package org.jenkins.plugins.opengrok;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;

import org.kohsuke.stapler.DataBoundSetter;
import org.opengrok.indexer.index.Indexer;

public class IndexerBuilder extends Builder implements SimpleBuildStep {
    private String dataRoot;
    private String srcRoot;
    private boolean historyEnabled;
    private boolean projectsEnabled;
    private boolean assignTags;
    private boolean verbose;

    @DataBoundConstructor
    public IndexerBuilder() {
        super();
    }


    public String getDataRoot() {
        return dataRoot;
    }

    @DataBoundSetter
    public void setDataRoot(String dataRoot) {
        this.dataRoot = dataRoot;
    }

    public String getSrcRoot() {
        return srcRoot;
    }

    @DataBoundSetter
    public void setSrcRoot(String srcRoot) {
        this.srcRoot = srcRoot;
    }

    public boolean isHistoryEnabled() {
        return historyEnabled;
    }

    @DataBoundSetter
    public void setHistoryEnabled(boolean historyEnabled) {
        this.historyEnabled = historyEnabled;
    }

    public boolean isProjectsEnabled() {
        return projectsEnabled;
    }

    @DataBoundSetter
    public void setProjectsEnabled(boolean projectsEnabled) {
        this.projectsEnabled = projectsEnabled;
    }

    public boolean isAssignTags() {
        return assignTags;
    }

    @DataBoundSetter
    public void setAssignTags(boolean assignTags) {
        this.assignTags = assignTags;
    }

    public boolean isVerbose() {
        return verbose;
    }

    @DataBoundSetter
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        /**
         * TODO
         *  bin/opengrok-indexer -J=-Xmx32g -J=-server -a /home/buildfarm/opengrok-1.5.12/lib/opengrok.jar --
         *  -s /work/buildfarm/src_root/github-other-master
         *  -d /work/buildfarm/data_root/github-other-master
         *  -H -P -S -G
         *  -W /work/buildfarm/data_root/github-other-master/configuration.xml
         *  -U http://10.0.12.92:8080/github-other-master/
         *
         */

        String[] strArray={"-s", "./src_root/github-other-master",
                            "-d", "./data_root/github-other-master",
                "-H", "-P", "-S", "-G",
                "-W", "./data_root/github-other-master/configuration.xml",
                "-U", "http://10.0.12.92:8080/github-other-master/"
        };
        Indexer.main(strArray);
    }

    @Symbol("opengrokIndexer")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.IndexerBuilder_DescriptorImpl_DisplayName();
        }

    }

}

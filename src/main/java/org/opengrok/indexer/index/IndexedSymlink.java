/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2019, Chris Fraire <cfraire@me.com>.
 */

package org.opengrok.indexer.index;

import java.io.File;

/**
 * Represents the associated data of a symlink indexed by {@link IndexDatabase}.
 */
public class IndexedSymlink {

    private final String absolute;
    private final String canonical;
    private final boolean isLocal;

    private final String canonicalSeparated;

    /**
     * Initializes an instance with the required arguments.
     * @param absolute a defined instance
     * @param canonical a defined instance
     * @param isLocal a value indicating if the symlink was local to a project.
     */
    IndexedSymlink(String absolute, String canonical, boolean isLocal) {
        if (absolute == null) {
            throw new IllegalArgumentException("absolute is null");
        }
        if (canonical == null) {
            throw new IllegalArgumentException("canonical is null");
        }
        this.absolute = absolute;
        this.canonical = canonical;
        this.isLocal = isLocal;
        this.canonicalSeparated = canonical + File.separator;
    }

    public String getAbsolute() {
        return absolute;
    }

    public String getCanonical() {
        return canonical;
    }

    public boolean isLocal() {
        return isLocal;
    }

    /**
     * Gets the value of {@link #getCanonical()} with a {@link File#separator}
     * appended.
     * @return a defined instance
     */
    public String getCanonicalSeparated() {
        return canonicalSeparated;
    }

    /**
     * Compares {@link #getAbsolute()}, {@link #getCanonical()}, and
     * {@link #isLocal()} to determine equality. (Generated by IntelliJ.)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IndexedSymlink that = (IndexedSymlink) o;

        if (isLocal != that.isLocal) {
            return false;
        }
        if (!absolute.equals(that.absolute)) {
            return false;
        }
        return canonical.equals(that.canonical);
    }

    /**
     * Gets a hashcode derived from {@link #getAbsolute()},
     * {@link #getCanonical()}, and {@link #isLocal()}. (Generated by IntelliJ.)
     */
    @Override
    public int hashCode() {
        int result = absolute.hashCode();
        result = 31 * result + canonical.hashCode();
        result = 31 * result + (isLocal ? 1 : 0);
        return result;
    }
}

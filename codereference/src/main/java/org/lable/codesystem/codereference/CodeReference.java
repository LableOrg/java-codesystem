/*
 * Copyright (C) 2015 Lable (info@lable.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lable.codesystem.codereference;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Immutable representation of a coded value with a reference to the standard or code system it is defined in.
 * <p>
 * For example, when referencing codes defined in ISO-standards, the following conventions are used:
 * <dl>
 *     <dt>codeSystem</dt>
 *     <dd>1.0.21298.4</dd>
 *     <dt>codeSystemName</dt>
 *     <dd>ISO/TS 21298</dd>
 *     <dt>code</dt>
 *     <dd>01</dd>
 *     <dt>displayName</dt>
 *     <dd>Subject of care</dd>
 * </dl>
 * This immutable value object stores this set of properties in a single object. The coded value shown above can be
 * constructed like this:
 * <pre>
 * {@code
 * new CodeReference("1.0.21298.4", "ISO/TS 21298", "01", "Subject of care");
 * }
 * </pre>
 * The coded values that can be represented by this class are not limited to ISO-defined standards. As long as the
 * {@link #codeSystem} string chosen uniquely identifies the code system within your data realm, any string can be used.
 * <p>
 * Instances of this class are considered equivalent if the combination of {@link #codeSystem} and {@link #code} is
 * the same in both instances, regardless of the other fields. Keep this in mind when comparing instances in a
 * context where {@link #codeSystemName}, {@link #displayName}, and {@link #originalText} should also be taken into
 * account.
 */
public class CodeReference implements Referenceable, Identifiable, Serializable {
    private static final long serialVersionUID = -3006506450060215824L;

    private final String codeSystem;
    private final String codeSystemName;
    private final String code;
    private final String displayName;
    private final String originalText;

    /**
     * Create a new CodeReference with the minimal set of required fields.
     * <p>
     * This minimal constructor should only be used for well-known local code definitions not intended to be exported.
     * {@link #CodeReference(String, String, String, String)} with all four fields non-null is preferred for most
     * code standards.
     *
     * @param codeSystem Object identifier (OID) reference.
     * @param code       The coded value itself.
     */
    public CodeReference(String codeSystem, String code) {
        this(codeSystem, null, code, null, null);
    }

    /**
     * Create a new CodeReference.
     *
     * @param codeSystem  Object identifier (OID) reference.
     * @param code        The coded value itself.
     * @param displayName Human readable display name of the code.
     */
    public CodeReference(String codeSystem, String code, String displayName) {
        this(codeSystem, null, code, displayName, null);
    }

    /**
     * Create a new CodeReference.
     *
     * @param codeSystem     Object identifier (OID) reference.
     * @param codeSystemName Name of the code system, optional parameter.
     * @param code           The coded value itself.
     * @param displayName    Human readable display name of the code.
     */
    public CodeReference(String codeSystem, String codeSystemName, String code, String displayName) {
        this(codeSystem, codeSystemName, code, displayName, null);
    }

    /**
     * Create a new CodeReference that also includes the original, unmodified text that the code was parsed from.
     *
     * @param codeSystem     Object identifier (OID) reference, or a unique local identifier for the code system.
     * @param codeSystemName Name of the code system, optional parameter.
     * @param code           The coded value itself.
     * @param displayName    Human readable display name of the code.
     * @param originalText   The original text the code was parsed from, optional parameter.
     */
    public CodeReference(String codeSystem, String codeSystemName, String code,
                         String displayName, String originalText) {
        parameterMayNotBeNull("codeSystem", codeSystem);
        parameterMayNotBeNull("code", code);

        this.codeSystem = codeSystem;
        this.codeSystemName = codeSystemName;
        this.code = code;
        this.displayName = displayName;
        this.originalText = originalText;
    }

    /**
     * @return Object identifier (OID) reference.
     */
    public String getCodeSystem() {
        return codeSystem;
    }

    /**
     * @return Name of the code system, may be null.
     */
    public String getCodeSystemName() {
        return codeSystemName;
    }

    /**
     * @return The coded value.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Human readable display name of the code.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return The original text the code was parsed from, may be null.
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * {@inheritDoc}
     * <p>
     * A {@link CodeReference} instance returns itself; no new instance will be created.
     */
    @Override
    public CodeReference toCodeReference() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> identifyingStack() {
        return Arrays.asList(getCodeSystem(), getCode());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof CodeReference)) {
            return false;
        }

        // Equal if the code system ID and the code itself match.
        CodeReference otherCodeReference = (CodeReference) other;
        return otherCodeReference.getCodeSystem().equals(getCodeSystem()) &&
                otherCodeReference.getCode().equals(getCode());
    }

    @Override
    public int hashCode() {
        return codeSystem.hashCode() * code.hashCode();
    }

    @Override
    public String toString() {
        return getDisplayName() == null
                ? getCodeSystem() + ": " + getCode()
                : getCodeSystem() + ": " + getCode() + " (" + getDisplayName() + ")";
    }

    public static <T> void parameterMayNotBeNull(String name, T parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("Parameter " + name + " is a required attribute (null passed).");
        }
    }
}

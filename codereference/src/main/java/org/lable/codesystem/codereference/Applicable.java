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

/**
 * Implementing classes represent code sets where the applicability of some of its codes is limited to a subset
 * of another set of codes.
 */
public interface Applicable {
    /**
     * Verify that the code represented by this object is applicable to a specific {@link Referenceable}.
     *
     * @param referenceable Code reference to test against.
     * @return True if the passed code reference can be applied to the code represented by this object.
     */
    default boolean appliesTo(Referenceable referenceable) {
        for (Referenceable applicableType : applicableTypes()) {
            if (referenceable.equals(applicableType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return A list of code references that can be used together with the codes represented by this class.
     */
    Referenceable[] applicableTypes();
}

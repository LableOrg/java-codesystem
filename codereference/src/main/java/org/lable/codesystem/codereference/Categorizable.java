/**
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

import java.util.List;

/**
 * Implementing classes define a set of {@link Referenceable} identifiers that categorize the identifier represented
 * by an instance of it.
 */
public interface Categorizable extends Referenceable {
    /**
     * @return Categories applicable to the current identifier.
     */
    List<Referenceable> categorizedUnder();
}

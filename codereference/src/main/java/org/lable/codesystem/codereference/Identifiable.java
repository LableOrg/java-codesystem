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

import java.util.List;

/**
 * Implementing classes return a list of elements that together identify an object.
 * <p>
 * Parts returned are ordered from the most global to the most specific part. E.g., a class that uniquely identifies
 * users by the combination of a 'realm' and a 'username' field, implements {@link #identifyingStack()} so that it
 * returns a list containing the values held by the 'realm' and 'username' fields, in that order.
 */
public interface Identifiable {
    /**
     * Get all identifying fields from an object. The size of the list returned is the same for every instance of a
     * specific objects, but parts in the list may be null.
     *
     * @return A list of identifier parts. The returned list may be immutable.
     */
    List<String> identifyingStack();
}

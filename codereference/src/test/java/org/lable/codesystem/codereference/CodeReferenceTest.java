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

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.lable.codesystem.codereference.CodeReference.parameterMayNotBeNull;

public class CodeReferenceTest {
    @Test
    public void getterTest() {
        CodeReference codeReference = new CodeReference("CS", "CSN", "XXX", "DN", "OT");

        assertThat(codeReference.getCodeSystem(), is("CS"));
        assertThat(codeReference.getCodeSystemName(), is("CSN"));
        assertThat(codeReference.getCode(), is("XXX"));
        assertThat(codeReference.getDisplayName(), is("DN"));
        assertThat(codeReference.getOriginalText(), is("OT"));
    }

    @Test
    public void referenceableTest() {
        CodeReference codeReference = new CodeReference("CS", "CSN", "XXX", "DN", "OT");

        // Returns itself.
        assertThat(codeReference.toCodeReference(), is(codeReference));
    }

    @Test
    public void toStringTest() {
        CodeReference codeReference = new CodeReference("CS", "CSN", "XXX", "DN", "OT");
        String expected = "CS: XXX (DN)";

        assertThat(codeReference.toString(), is(expected));
    }

    @Test
    public void toStringWithNullFieldsATest() {
        CodeReference codeReference = new CodeReference("CS", null, "XXX", "DN");
        String expected = "CS: XXX (DN)";

        assertThat(codeReference.toString(), is(expected));
    }

    @Test
    public void toStringWithNullFieldsBTest() {
        CodeReference codeReference = new CodeReference("CS", "XXX", "DN");
        String expected = "CS: XXX (DN)";

        assertThat(codeReference.toString(), is(expected));
    }

    @Test
    public void equalsTest() {
        CodeReference codeReferenceA = new CodeReference("CS", "XXX", "need-not-match");
        CodeReference codeReferenceB = new CodeReference("CS", "XXX", "nëëd-nøt-mätch");

        assertThat(codeReferenceA.equals(codeReferenceB), is(true));
    }

    @Test
    public void notEqualCodeTest() {
        CodeReference codeReferenceA = new CodeReference("CS", "XXX12", "DN");
        CodeReference codeReferenceB = new CodeReference("CS", "XXX02", "DN");

        assertThat(codeReferenceA.equals(codeReferenceB), is(false));
    }

    @Test
    public void notEqualCodeSystemTest() {
        CodeReference codeReferenceA = new CodeReference("CS-001", "1", "DN");
        CodeReference codeReferenceB = new CodeReference("CS-403", "1", "DN");

        assertThat(codeReferenceA.equals(codeReferenceB), is(false));
    }

    @Test
    public void equalsNullTest() {
        CodeReference codeReference = new CodeReference("CS-001", "1", "DN");

        assertThat(codeReference.equals(null), is(false));
    }

    @Test
    public void equalsWrongTypeTest() {
        CodeReference codeReference = new CodeReference("CS-001", "1", "DN");

        assertThat(codeReference.equals("XXX"), is(false));
    }

    @Test
    public void hashCodeTest() {
        CodeReference codeReferenceA = new CodeReference("CS-001", "1", "DN");
        CodeReference codeReferenceB = new CodeReference("CS-001", "Some code system", "1", "DN", "OT");

        // Equality is based on the combination of code system and code only.
        assertThat(codeReferenceA.hashCode(), is(codeReferenceB.hashCode()));
    }

    @Test
    public void parameterMayNotBeNullTest() {
        parameterMayNotBeNull("name", "parameter");
    }

    @Test(expected = IllegalArgumentException.class)
    public void parameterMayNotBeNullWithNullTest() {
        parameterMayNotBeNull("name", null);
    }
}
/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2016 The ZAP Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.admin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.zaproxy.zap.control.AddOn;

public class HelpReportMissing extends AddOnsTask {

    private static void reportMissingHelp(Set<AddOnData> addOns, AddOn.Status status) {
        for (AddOnData addOnData : addOns) {
            if (status.equals(addOnData.getStatus())) {
                Path helpdir = addOnData.getDir().resolve("resources/help");
                if (!Files.isDirectory(helpdir)) {
                    System.out.println(addOnData.getId());
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Set<AddOnData> addOns = getAllAddOns();
        System.out.println("Release add-ons");
        System.out.println("===============");
        reportMissingHelp(addOns, AddOn.Status.release);

        System.out.println();
        System.out.println("Beta add-ons");
        System.out.println("============");
        reportMissingHelp(addOns, AddOn.Status.beta);

        System.out.println();
        System.out.println("Alpha add-ons");
        System.out.println("============");
        reportMissingHelp(addOns, AddOn.Status.alpha);
    }
}

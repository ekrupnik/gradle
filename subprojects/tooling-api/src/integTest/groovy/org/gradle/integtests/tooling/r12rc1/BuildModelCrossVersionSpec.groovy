/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.integtests.tooling.r12rc1

import org.gradle.integtests.tooling.fixture.TargetGradleVersion
import org.gradle.integtests.tooling.fixture.ToolingApiVersion
import org.gradle.integtests.tooling.fixture.ToolingApiSpecification
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.model.eclipse.HierarchicalEclipseProject

@ToolingApiVersion(">=1.2-rc-1")
@TargetGradleVersion(">=1.2-rc-1")
class BuildModelCrossVersionSpec extends ToolingApiSpecification {
    def "can run tasks before building Eclipse model"() {
        file('build.gradle').text = '''
apply plugin: 'java'

task setup << {
    println "run"
    project.description = 'this is a project'
}
'''

        when:
        HierarchicalEclipseProject project = withConnection { ProjectConnection connection ->
            connection.model(HierarchicalEclipseProject.class).forTasks('setup').get()
        }

        then:
        project.description == 'this is a project'
    }
}

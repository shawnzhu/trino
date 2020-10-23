/*
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
package io.prestosql.server;

import com.google.common.collect.ImmutableMap;
import io.airlift.units.Duration;
import org.testng.annotations.Test;

import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;
import static java.util.concurrent.TimeUnit.MINUTES;

public class TestServerConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(ServerConfig.class)
                .setCoordinator(true)
                .setIncludeExceptionInResponse(true)
                .setGracePeriod(new Duration(2, MINUTES)));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = new ImmutableMap.Builder<String, String>()
                .put("coordinator", "false")
                .put("http.include-exception-in-response", "false")
                .put("shutdown.grace-period", "5m")
                .build();

        ServerConfig expected = new ServerConfig()
                .setCoordinator(false)
                .setIncludeExceptionInResponse(false)
                .setGracePeriod(new Duration(5, MINUTES));

        assertFullMapping(properties, expected);
    }
}

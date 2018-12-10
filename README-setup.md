## Prerequisites

- Chrome 70.0 or later
- JDK 1.8.x
- maven 3.5.x
- [Alfresco maven repositories](#alfresco-maven-repository-configuration) configured
- Java IDE (e.g. Intellij, Eclipse)

## Running tests in maven

1. Start DBP instance (refer to [Deploying Alfresco Content Services using Docker Compose](https://github.com/Alfresco/acs-deployment/blob/master/docs/docker-compose-deployment.md) 
   for more details)
2. Verify that following properties in `src/main/resources/default.properties` match that of the
   running dbp instance:
    ```properties
    # Alfresco HTTP Server Settings
    alfresco.scheme=http
    alfresco.server=localhost
    alfresco.port=8080
        
    # Administrator Credentials
    admin.user=admin
    admin.password=admin
    admin.name=Administrator
        
    # set browser type: Firefox, Chrome
    browser.name=Chrome
    browser.version=
    env.platform=MAC
    ```
3. Run UI tests:
    ```bash
    $ mvn clean test
    ```
    If you get maven authentication exceptions, verify the access to Alfresco maven
    repositories is configured as described below.

## Alfresco maven repository configuration
In your home directory create a file called `.m2/settings.xml` with the following
content. 

Replace `YOUR USERNAME` with the LDAP username of a user with access to this repository.

Replace `YOUR ENCRYPTED PASSWORD` with `YOUR USERNAME`'s encrypted password (see the 
[Maven Password Encryption](https://maven.apache.org/guides/mini/guide-encryption.html) guide for more details).
```xml
<settings>
  <profiles>
    <profile>
      <id>alfresco-internal</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <id>alfresco-internal</id>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
          <name>Alfresco Internal Repository</name>
          <url>https://artifacts.alfresco.com/nexus/content/groups/internal/</url>
        </repository>

        <repository>
          <id>alfresco-staging</id>
          <releases>
            <enabled>true</enabled>
          </releases>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
          <name>Alfresco Staging Repository</name>
          <url>https://artifacts.alfresco.com/nexus/content/groups/staging/</url>
        </repository>

      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>alfresco-internal</id>
          <name>Alfresco Internal Repository</name>
          <url>https://artifacts.alfresco.com/nexus/content/groups/public</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>
  <servers>
    <server>
      <id>alfresco-internal</id>
      <username>YOUR USERNAME</username>
      <password>YOUR ENCRYPTED PASSWORD</password>
    </server>
    <server>
      <id>alfresco-staging</id>
      <username>YOUR USERNAME</username>
      <password>YOUR ENCRYPTED PASSWORD</password>
    </server>
  </servers>
</settings>
```

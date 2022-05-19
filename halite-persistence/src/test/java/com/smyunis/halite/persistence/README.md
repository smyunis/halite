## WARNING

#### These test are integration test that do real database calls (Database access is **NOT** mocked here).So make sure you have time to kill before you start the test in this package.

If you are working on a new database you should also configure
`flyway` in `build.gradle` to migrate the SQL DDL scripts to create the tables.
Run the following in the terminal before you run the test in this package.

`./gradlew :halite-persistence:flywayMigrate`

You can configure the test database credentials in
`com.smyunis.halite.persistence.DatabaseFixture.java`

Just make sure its "pointing" to the same database you migrated.

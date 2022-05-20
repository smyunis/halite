## WARNING

#### These test are integration test that do real database calls (Database access is **NOT** mocked here).So make sure you have time to kill before you start the test in this package.

If you are working on a new database you should also configure
`flyway` in `build.gradle` to migrate the SQL DDL scripts to create the tables.
Run the following in the terminal before you run the test in this package.

`./gradlew :halite-persistence:flywayMigrate`

* You must **EXCLUDE** the migration script named `V1.1__ForeignKeyConstraints.sql`
for the tests to work. Database foreign key constraints make it impossible to test each respository 
in isolation. In order to exclude the script simply rename the file to what ever you like
so that flyway will not pick it up.

You can configure the test database credentials in
`com.smyunis.halite.persistence.DatabaseFixture.java`

Just make sure its "pointing" to the same database you migrated.

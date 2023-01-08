# Project API Automation using Rest-Assured

## Setup project

**Điều kiện tiên quyết**
- Cài đặt Java 8 trở lên
- Sử dụng Maven từ 3.5 trở lên
- Dùng IDE tùy chọn (recommend chọn Intellij IDEA)

Lưu ý:
setup proxy cho maven.

`Edit file: apache-maven-3.6.3\conf\settings.xml`

```
<proxies>
      <proxy>
        <id>proxy</id>
        <active>true</active>
        <protocol>http</protocol>
        <host>ip</host>
        <port>port</port>
      </proxy>
</proxies>
```

## Project Structure

```
+ api
    HTTP request for each API
+ model
    Domain model classes
+ helper
    Helper classes
+ features
    Write test for each API (Every test classes extends Base Class)
```

## How to run test

- Run toàn bộ test cases

`mvn clean test`

- Run Test theo Tags

`mvn clean test -Dgroups="tagName"`

- Run Test theo environment (xem environment trong file _src/main/resources/application.conf_)

`mvn clean test -Denvironment="environmentName" `

- Run Test theo environment & tags

`mvn clean test -Dgroups="tagName" -Denvironment="environmentName" `


## Reporting
`mvn allure:report`

The Allure reports will be generated in the `target/site/allure-maven-plugin` directory.
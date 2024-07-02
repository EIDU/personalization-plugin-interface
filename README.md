# EIDU Personalization Plugin Interface

![Maven Central](https://img.shields.io/maven-central/v/com.eidu/personalization-plugin-interface)

This interface defines critical API components needed for building your personalization plugins to use within the EIDU platform. 

Please note:
- Plugins submitted are sand-boxed at runtime to prevent them from causing malicious or unintentional harm.
- Every new plugin revision submitted will require the source undergo review by EIDU engineering.

## Usage

To add the Gradle dependency:

```
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.eidu:personalization-plugin-interface:<version>")
}
```

Please consult [dev.eidu.com](https://dev.eidu.com/personalization/intro) for instructions and detailed API documentation.

## Support

Please contact EIDU at [dev@eidu.com](mailto:dev@eidu.com) if you need help.

## Contributing

We are excited about your contributions. See
[our current contribution guidelines](https://dev.eidu.com/contributing/overview) to get started.

## License

This library is published under the [MIT License](https://github.com/EIDU/personalization-plugin-interface/blob/main/LICENSE).

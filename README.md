# V2C Desktop Controller for Linux

*Copyright (c) 2020 V2C Development Team. All rights reserved.*

## Build

You need Java 11. This project can be tested and compiled with the following command.
    
`chmod u+x ./gradlew`
`gradlew clean shadowJar`

## Execution

To run it, just do `java -jar build\libs\v2c-desktop-controller-linux.jar`.

You can optionally specify some command-line arguments.

|Short Param|Long Param|Description        |Default|
|:----------|:---------|:------------------|:------|
|-p         |--port    |The port number    |5698   |

## License

**This repository is subject to Version 0.0.1 of the [V2C License](https://tinyurl.com/v2c-license).**

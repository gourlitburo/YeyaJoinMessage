# YeyaJoinMessage

A simple Spigot plugin for customization of join and quit messages.

## Features

* Join message to player
* Join and quit broadcasts
* Enter/leave Nether/End broadcasts

## Configuration

### Format

```yaml
# file: config.yml
msg:
  <message key>:
    enable: [true | false]
    text: <format string>
```

Available message keys:

* `join`
* `join_broadcast`
* `quit_broadcast`
* `enter_nether_broadcast`
* `leave_nether_broadcast`
* `enter_end_broadcast`
* `leave_end_broadcast`

### Parameter interpolation

Syntax: `#{PARAMETER_NAME}`

Available parameters:
* `NAME`
* `DISPLAYNAME`
* `TIME`
* `PLAYERCOUNT`

### Example

See [default config.yml](https://github.com/gourlitburo/YeyaJoinMessage/blob/master/config.yml).

## Building

0. Have a JDK<sup>*</sup> and Ant installed
1. Put [Spigot API](https://www.spigotmc.org/wiki/buildtools/) 1.14(.3) and [StringFormatter](https://github.com/gourlitburo/StringFormatter) jars in `lib` directory
2. Run `ant`

Output jar will be in `target` directory.

<sup>* Developed on Java 12. Lower versions might work too.</sup>

## License

```
Copyright 2019 Zachary Guard

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

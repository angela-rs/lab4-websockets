# Web Engineering 2020-2021 / Websockets
**In this assignment your PR must only modify the `README.md` file**.
Please, go to the [Wiki](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets/wiki) in order to get the instructions for this assignment.

## Historical background

The code implements the script DOCTOR of the [ELIZA](https://en.wikipedia.org/wiki/ELIZA) system and provides you a client and server that communicate each other by means of websockets.
ELIZA is an early example of primitive natural language processing developed by [Joseph Weizenbaum](https://en.wikipedia.org/wiki/Joseph_Weizenbaum) between 1964 and 1966.
ELIZA operated by processing users' responses to scripts, the most famous of which was DOCTOR, a simulation of a [Rogerian psychotherapist](https://en.wikipedia.org/wiki/Person-centered_therapy).
Using almost no information about human thought or emotion, DOCTOR sometimes provided a startlingly human-like interaction.

## Primary goal

The objective is to complete `ElizaServerTest.kt` by completing the test `onChat` that now is ignored.
The `onChat` test must:

* Send a message from the client to the server that is running a Java implementation of the script DOCTOR.
* Check the client receives a DOCTOR question about your mental health (see examples in `Eliza.kt`).

You must remove `@Disabled` and place up to 6 lines of code to fulfil the task.
`ElizaServerTest.kt` includes the test `onOpen` that will provide you insights.

## Secondary goals (:gift:)

Solutions must meet primary goals too.

Proposed:

- Support SockJS in the server side and show that xhr-polling can be used as transport instead of WebSocket when needed ([additional info](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-fallback-sockjs-client)).
- Turn the DOCTOR into a client of the server, so, the server acts only as message broker.
- Use the server as a relay server to connect to an external message broker (e.g. RabbitMQ, [additional info](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-handle-broker-relay))

Note: unless the goal specifies o disallows a specific framework you are free to replace the framework used in the original implementation with a different framework.

Manifest your intention first by a PR updating this `README.md` with your goal.
If you desist of your goal, release it by a PR so other fellow can try it.

| User name | NIA | CI | Solution | Score |
|--------|-----------|------|--------------|-------------|
|[UNIZAR-30246-WebEngineering](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets) | 30246 | [![Build Status](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/UNIZAR-30246-WebEngineering/lab4-websockets/actions/workflows/ci.yml) |
|[Diego Marco](https://github.com/dmarcob/lab4-websockets/tree/test) | 755232 | [![Build Status](https://github.com/dmarcob/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/dmarcob/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/dmarcob/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt) | [Support STOMP in the server side and create a small client that uses it](https://github.com/dmarcob/lab4-websockets/blob/gift/documentation.md) :gift:
|[Óscar Pueyo](https://github.com/iksopo/lab4-websockets) | 780378 | [![Build Status](https://github.com/iksopo/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/iksopo/lab4-websockets/actions/workflows/ci.yml) | [Solution](https://github.com/iksopo/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[María Peña](https://github.com/Keyleth8/lab4-websockets/tree/test) | 780448 | [![Build Status](https://github.com/Keyleth8/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/Keyleth8/lab4-websockets/actions/workflows/ci.yml) | [Solution](https://github.com/Keyleth8/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt) |
|[Mateo Vallejo](https://github.com/CursedR3N/lab4-websockets) | 780029 | [![Build Status](https://github.com/CursedR3N/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/CursedR3N/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/CursedR3N/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt) |
|[Alejandro Magallón](https://github.com/alecron/lab4-websockets/tree/test) | 779354 | [![Build Status](https://github.com/alecron/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/alecron/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/alecron/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Tomás Pelayo](https://github.com/Tomenos18/lab4-websockets) | 779691 | [![Build Status](https://github.com/Tomenos18/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/Tomenos18/lab4-websockets/actions/workflows/ci.yml) | [Solution](https://github.com/Tomenos18/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Guillermo Cánovas González](https://github.com/guillecanovas/lab4-websockets) | 755848 | [![Build Status](https://github.com/guillecanovas/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/guillecanovas/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/guillecanovas/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Jorge Laguna](https://github.com/topopelon/lab4-websockets/tree/test) | 735550 | [![Build Status](https://github.com/topopelon/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/topopelon/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/topopelon/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt) | [Demostration of use with Postman](https://github.com/topopelon/lab4-websockets/blob/test/description.md) :gift:
|[Fernando Serrano](https://github.com/Feer93/lab4-websockets) | 774840 | [![Build Status](https://github.com/Feer93/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/Feer93/lab4-websockets/actions/workflows/ci.yml) | [Solution](https://github.com/Feer93/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Pablo Jordán](https://github.com/pabloJordan24/lab4-websockets/tree/test) | 757166 | [![Build Status](https://github.com/pabloJordan24/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/pabloJordan24/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/pabloJordan24/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Andoni Salcedo Navarro](https://github.com/AndoniSalcedo/lab4-websockets) | 785649 | [![Build Status](https://github.com/AndoniSalcedo/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/AndoniSalcedo/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/AndoniSalcedo/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Jorge Serrano](https://github.com/zgzserrano/lab4-websockets) | 776453 | [![Build Status](https://github.com/zgzserrano/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/zgzserrano/lab4-websockets/actions/workflows/ci.yml) | [solution](https://github.com/zgzserrano/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Germán Garcés](https://github.com/fntkg/lab4-websockets) | 757024 | [![Build Status](https://github.com/fntkg/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/fntkg/lab4-websockets/actions/workflows/ci.yml) | [Solution](https://github.com/fntkg/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Sergio Benítez Mombiela](https://github.com/SergioBenitez755787/lab4-websockets/tree/test) | 755787 | [![Build Status](https://github.com/SergioBenitez755787/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/SergioBenitez755787/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/SergioBenitez755787/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Fede Barcelona](https://github.com/tembleking/lab4-websockets/tree/test) | 666151 | [![Build Status](https://github.com/tembleking/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/tembleking/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/tembleking/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Jaime Conchello](https://github.com/jaimecb/lab4-websockets/tree/test) | 776012 | [![Build Status](https://github.com/jaimecb/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/jaimecb/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/jaimecb/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Alejandro Artal](https://github.com/Alejandro-Artal/lab4-websockets/tree/test) | 775929 | [![Build Status](https://github.com/Alejandro-Artal/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/Alejandro-Artal/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/Alejandro-Artal/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Diego García Muro](https://github.com/thdgm/lab4-websockets.git/tree/test) | 767870 | [![Build Status](https://github.com/thdgm/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/thdgm/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/thdgm/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Héctor Bara](https://github.com/dolansete/lab4-websockets/tree/test) | 778097 | [![Build Status](https://github.com/dolansete/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/dolansete/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/dolansete/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Salomé Rea](https://github.com/SalomeReav/lab4-websockets/tree/test)| 720162| [![Build Status](https://github.com/SalomeReav/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/SalomeReav/lab4-websockets/actions/workflows/ci.yml) | [Solution](https://github.com/SalomeReav/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Hector Herrmann](https://github.com/HNHerrmann/lab4-websockets)| 646946| [![Build Status](https://github.com/HNHerrmann/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/HNHerrmann/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/HNHerrmann/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
|[Ángela Rojo Sediles](https://github.com/angela-rs/lab4-websockets)| 646946| [![Build Status](https://github.com/angela-rs/lab4-websockets/actions/workflows/ci.yml/badge.svg)](https://github.com/angela-rs/lab4-websockets/actions/workflows/ci.yml) | [ElizaServerTest](https://github.com/angela-rs/lab4-websockets/blob/test/src/test/kotlin/websockets/ElizaServerTest.kt)
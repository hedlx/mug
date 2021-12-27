# μG

[![Mug CI](https://github.com/hedlx/mug/actions/workflows/deploy.yml/badge.svg)](https://github.com/hedlx/mug/actions/workflows/deploy.yml)

Wannabe music synthesizer.

## Develop
```
yarn build && lein figwheel
```

## Build
```
yarn build && lein do clean, cljsbuild once prod
```

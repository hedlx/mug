#!/usr/bin/env bash
set -e

rm -rf prod/

yarn prod
lein cljsbuild once prod

deps=`ls -1 prod/js | grep 'deps.*\.js' | head -n 1`
app_sha=`sha256sum prod/js/app.js | cut -c -20`
app="app.${app_sha}.js"

mv prod/js/app.js prod/js/${app}

cat <<EOF > prod/index.html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <link rel="stylesheet" href="https://unpkg.com/tachyons@4.12.0/css/tachyons.min.css"/>
        <link rel="icon" href="https://clojurescript.org/images/cljs-logo-icon-32.png">
    </head>
    <body>
        <div id="app"/>
        <script src="js/${deps}" type="text/javascript"></script>
        <script src="js/${app}" type="text/javascript"></script>
    </body>
</html>
EOF

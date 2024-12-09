# Playwright

## Anpassungen

### Netzwerkkonfiguration

#### Proxy Konfiguration

Proxy Server: `HTTPS_PROXY=<PROXY-ADDR>`
tbs no_proxy

### Browser Storage Location

#### Default

- Windows: `%USERPROFILE%\AppData\Local\ms-playwright`
- Linux: `~/.cache/ms-playwright`

#### Konfiguration

Mit Setzen der Umgebungsvariable

`PLAYWRIGHT_BROWSERS_PATH=$HOME/pw-browsers`

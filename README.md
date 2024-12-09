# Playwright

## Konfiguration

Konfiguration in der Regel über das Setzen von Umgebungsvariablen

### Netzwerk

#### Proxy

Proxy Server: 

`HTTPS_PROXY=<PROXY-ADDR>`

tbs no_proxy

#### CA

`NODE_EXTRA_CA_CERTS="/path/to/cert.pem"`

### Browser Storage Location

#### Default

- Windows: `%USERPROFILE%\AppData\Local\ms-playwright`
- Linux: `~/.cache/ms-playwright`

#### Konfiguration

Mit Setzen der Umgebungsvariable

`PLAYWRIGHT_BROWSERS_PATH=$HOME/pw-browsers`

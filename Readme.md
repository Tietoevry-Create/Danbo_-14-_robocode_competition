# Welcome one and all to the great Robocode tournament!

## Rules

1. Create a bot that can fight against other bots.
1. Create a pull request to this repository with your bot in the `competition_bots` folder.
1. The bot must be a single Java class that extends `robocode.Robot`. Make sure that the class has the same name as the file.
1. Enjoy the fireworks!

## How to create a bot

See the [Getting Started](https://robowiki.net/wiki/Robocode/Getting_Started) page on the Robocode wiki.

## Running Robocode locally

1. Install TigerVNC
   - Download binary for [windows](https://sourceforge.net/projects/tigervnc/files/stable/1.12.0/tigervnc-winvnc-1.12.0.exe/download) or [MacOS](https://sourceforge.net/projects/tigervnc/files/stable/1.12.0/TigerVNC-1.12.0.dmg/download)
   - Or use your favorite package manager
1. Clone this repo
1. Make sure docker in installed and the docker deamon is running
1. Run `docker build -t robocode-competition .`
1. Run robocode container `docker run -p 5900:5900 robocode-competition`
1. Connect to the container: `vncviewer localhost` or run TigerVNC and connect to the server on `localhost:5900`

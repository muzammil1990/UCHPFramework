# uchp-client-angular

## UCHP is a system used for handling claims in a unified way in all markets.
### The purpose of UCHP is to secure that the customers are handled in the same way in all markets as well as increase the efficiency of the warranty process.

Notice:
Information found in UCHP is confidential business information and is to be made available only to the approved user and its designated employees.
It contains privileged, confidential and proprietary information and any unauthorized use or distribution of this information,
by or to persons other than approved user and its designated employees, is strictly prohibited.

This project was generated with the [Angular Full-Stack Generator](https://github.com/DaftMonk/generator-angular-fullstack) version 3.3.0.
Added TypeScript support from JSVS DRS UI 1.0.
Now supported with git and WebStorm (settings-->Version Control-->Git ,and then, In the SSH executable dropdown, choose Native)

dev branch
## Getting Started

### Prerequisites

- [Git](https://git-scm.com/)
- [Node.js and npm](nodejs.org) Node ^4.2.3, npm ^2.14.7
- [Bower](bower.io) (`npm install --global bower`)
- [Ruby](https://www.ruby-lang.org) and then `gem install sass`
- [Grunt](http://gruntjs.com/) (`npm install --global grunt-cli`)
- [MongoDB](https://www.mongodb.org/) - Keep a running daemon with `mongod`

### Developing

1. Run `npm install` to install server dependencies.

2. Run `bower install` to install front-end dependencies.

3. Run `mongod` in a separate shell to keep an instance of the MongoDB Daemon running

4. Run `grunt serve` to start the development server. It should automatically open the client in your browser when ready.

## Build & development

Run `grunt build` for building and `grunt serve` for preview.

## Testing

Running `npm test` will run the unit tests with karma.

test

## Volvo proxies settings

Installation of front-end developer tools
Introduction
It’s very common when you start to develop web applications @Volvo that you will face some issues with firewalls prior to install and setup your
front-end development environment. This article will give you light and a path to follow to work with tools like Node.js/NPM, Bower and Git.
Proxy gateways @Volvo
There are many proxy gateways within the company. It depends on the site you work on. Choose the nearest location to you from the list below:
Site Proxy name Port
GOT httppxgot.srv.volvo.com 8080
GSO httppxgso.srv.volvo.com 8080
LYON httppxlyon.srv.volvo.com 8080
GENT httppxgent.srv.volvo.com 8080
CTA httppxcta.srv.volvo.com 8080
KR httppxkr.srv.volvo.com 8080
SYD httppxsyd.srv.volvo.com 8080
BLR pxblr1.blr.volvo.net 8080
JPN httppxsait.srv.volvo.com 8080
Up-to-date list of locations is avaliable under this link.
Node.js and NPM
Installation
Download the latest LTS version suitable for your operating system and install node.js
NPM comes packed with node.js, but we need to update to the latest version and make it globally available. Do it by running following
command:

npm -g install npm

Verify your installation by running following command(s). If everything is correctly installed you will get the version number of node and npm
In the code snippets provided below you will need to apply following adjustments :
vcn_user has to be set to your VCN username
pass has to be set to your VCN password
proxy_gateway has to be set as your closest proxy gateway
Make sure that your password does not contain characters that could cause the URL to be misinterpreted, e.g. '@', ':', '?', '!', '/' .
Other alternative is to escape illegal characters in tools configuration.
Please notice that gateway could be different if you work from home connecting to MyAccess gateway or in the office.

node --version
npm --version

### Proxy configuration

To add your proxy configuration settings run following command:

npm config set proxy http://vcn_user:pass@proxy_gateway:proxy_gateway_port
npm config set https-proxy http://vcn_user:pass@proxy_gateway:proxy_gateway_port

Results of that command should be creation of .npmrc file under c:\users\%vcn_user%.
Verify your proxy setting by running following command:
npm config get proxy

### Git
Installation
Download and install Git from http://git-scm.com/download.
Once installed you should add Git executable file to windows PATH in order to be able to run git commands from windows
command-line:
create HOME environment variable pointing to your user profile (eg. c:\Users\%user_id% on Windows 7)
check in installation wizard to add Git executable to the PATH; Note if you skip it you will need to add it manually to the PATH
variable.

Once installed you should have access to the git command line tool, verify by running following command:

git --version

### Proxy configuration

Git per default stores custom configuration under c:\users\%vcn_user%, the file is .gitconfig. To add your proxy configuration settings
run following commands:

NPM per default stores custom configuration under c:\users\%vcn_user%, the file is .npmrc. But this setup limitates to only one
registry and proxy configuration. For example to be able to download DRS UI reference applications it will require settings without the
proxy because the registry resides within Volvo network.
slc tool by StrongLoop could be used on Windows for handling of different npm registry profiles, however due to its depenency on

VisualStudio (please check disclaimer below) it is not recommended choice for now.

git config --global url."https://".insteadOf git://
git config --global http.proxy
http://vcn_user:pass@proxy_gateway:proxy_gateway_port
git config --global https.proxy
https://vcn_user:pass@proxy_gateway:proxy_gateway_port
Verify your git configuration by running following commands:
git config --global --get http.proxy
git config --global --get https.proxy

### Bower
Installation
Prerequisite for Bower is NPM, if you have it installed and configured proceed with next step
Install Bower, and make it globally available on your machine, by running following command
npm -g install bower
Verify your installation by running following command. If everything is correctly installed you will get the version number of the bower tool

bower --version

### Proxy configuration
Create new file ".bowerrc" and save it under "c:\users\%your_vcn_account%\"

Add following contents to file .bowerrc

{
"proxy": "http://vcn_user:pass@proxy_gateway:proxy_gateway_port",
"https-proxy": "http://vcn_user:pass@proxy_gateway:proxy_gateway_port",
"strict-ssl": false
}

### Grunt

Prerequisite for Grunt is NPM, if you have it installed and configured proceed with next step
Install Grunt, and make it globally available on your machine, by running following command:
npm -g install grunt
npm -g install grunt-cli
Pay attention to the fact that there is no https protocle on https-proxy option of the configuration.

Verify your installation by running following command. If everything is correctly installed you will get the version number of the grunt and
grunt-cli tool:
grunt --version

### Python
Download and install Python 2.7.3 suitable for version and architecture of your operating system (Observe! Version is required to be
2.7.3).
Add Pyhton exe to your system PATH environment variable.

Some modules of node might require compilation on the destination platform. This means that binaries had to be generated on your machine and
certain compilators needs to be provided. For Windows OS, the requirement is Visual Studio 2013 Express for Desktop. For more please refer to
node-gyp installation instructions. Note that Express version of Visual Studio cannot be used in commercial projects, and you might need to order
licensed version from Faros.

Examples of such modules are strongloop or grunt-node-inspector. In angular-fullstack-generator package.json file, following dependency should
be removed, otherwise Visual Studio will be required as prerequisite.
"grunt-node-inspector": "~0.1.5"

### MongoDB
Installation

Download latest version of MongoDB and run the installation wizard.

Create default folder for Mongo data: 'c:\data'.

Make sure MongoDB binaries (e.g. C:\Program Files\MongoDB\Server\3.0\bin) are added to the system PATH environment variable. You

can verify it with running following command:

mongod

Yeoman generator
Yeoman is scaffolding tool that will help with project structure, build scripts, test configuration and many others. We recommend using angular-full
stack-generator as a project starting point. Next please refer to POS and reference architecture to evolve towards component based direction.

Disclaimer regarding requirement for Python and VisualStudio 2013

Some npm plugins need node-gyp to be installed.

However, node-gyp has it's own dependencies see github page where dependencies on Windows are listed and described

Due to these problems, as described on stackoverflow, it's required to install both Python and Visual Studio Express for Desktop prior to
fully utilize NPM on Windows.
There is an initiative and request to include some additional packages within node to avoid these additional steps, see here
Visual Studio 2013 disclaimer



# FrontEnd Defintion of Done (DoD)

Code should be commented, reviewed and checked in and run against current version in source control
Deployed to system test environment and passed system tests
Passed UAT (User Acceptance Testing) and signed off as meeting requirements
Any build/deployment/configuration changes implemented/documented/communicated
Relevant documentation/diagrams produced and/or updated
Remaining hours for task set to zero and task closed

Ensure ode is consistent with following principles and conventions:
•application structure
•coding conventions
•file naming conventions
•architectural principles
•builds without errors
•code has been covered by unit tests, that have passed build in CI environment

All the acceptance criteria have been met
Functionality has been successfully launched on a test server

# Generating documentation

$npm install -g typedoc

remove folder.gitkeep

typedoc --out doc/ client/

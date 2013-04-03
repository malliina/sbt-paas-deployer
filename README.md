# SBT PaaS Deployer #

This SBT plugin deploys apps to Platform as a Service (PaaS) providers.

## Usage ##

Use the following two keys to deploy the app to your favorite PaaS provider
    provider_name:paas-push
    provider_name:paas-update

Example: af:paas-push

## Existing support ##

- AppFog (af)
- CloudFoundry (cf)
- Heroku (heroku)

Cloudbees has its own SBT plugin. Use it.

## Prerequisites ##

The command line tools of the respective PaaS providers need to be installed as a prerequisite.

## Known issues ##
- Before pushing to AppFog/CloudFoundry, login using the command line tools (af login / vmc login)

## Help ##
- After a git clone of a heroku app, execute:
    git remote add heroku git@heroku.com:{heroku-app-name}.git

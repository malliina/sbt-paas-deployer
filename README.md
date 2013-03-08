Deploys apps to Platform as a Service (PaaS) providers.

Provides two main SBT keys: paas-push and paas-update.

Usage: <provider>:push, <provider>:update
- Example: af:paas-push

Existing support:

- AppFog (af)
- CloudFoundry (cf)
- Heroku (heroku)

Cloudbees has its own SBT plugin.

The command line tools of the respective PaaS providers need to be installed as a prerequisite.

Known issues:
- Before pushing to AppFog/CloudFoundry, login using the command line tools (af login / vmc login)

# bblm 
A League Manager for Blood Bowl

# Usage

Currently, don't. 

There are a few basic features that need to be resolved before you can consider this 'ready', and even then, it'll be a while till it's good enough. 

When this is ready, the idea would be that you run this on a server that you host. But along with the built file (still not sure what form that will take) you will need to run a few other things.

You are going to want to secure the connection between the users and the servers. That's 'best' done by using nginx as a reverse proxy. Let's encrypt is a good way to the certificates for encryption. I might at some point look to get this system able to send out emails, but that's a long way off. 

# Aims

This is going to be tool for managing Leagues of Blood Bowl. To start with at least, it will aim to support the 2016 version of the game and the league rules provided in the supplement Death Zone. Over time, if there is demand I can look to support other styles of league play.

The end system will be private by default, such that only registered 'coaches' are able to see anything of the system. I will look to make it so that sections can be made public.

There will be two halfs to this system. There will be the back end logic and data store that will be developed in Kotlin. The front side will be standard web tech that should be purely static, such that a basic nginx server can host it. The two will talk via, what I hope will be, a clean api, such that if you want, you can implement your own front end system.

All of the 'core' data that would be required, such as the stats for players, the names of teams or the names of skills, will be loaded via a configuration file that would need to be provided. I will provide a sample data that should make it clear enough how to configure this.

# Disclaimer

I do not claim any copyright ownership of the terms 'Blood Bowl' or 'Death Zone'.

I will not host any terms or data that could be considered copyrighted or 'owned' by a third party.
 
 If you feel I am infringing on your ownership of anything, please contact me so I can resolve it.
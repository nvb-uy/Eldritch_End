See the full changelog in https://github.com/nvb-uy/Eldritch_End/blob/1.20.1/CHANGELOG.md

## 0.3.0 (Public Release)
- API BREAKING CHANGE! Corruption Resistance is no longer percentile and instead it is flat
- WIKI IS OUT! https://rpg.prominence.wiki/mod/eldritch-end
- Added The Faceless Boss [Joespeph, ElocinDev]
- Added Corruption System [ElocinDev, Joespeph]
- Added Ritual System, used to summon The Faceless at the moment [ElocinDev]
- Added Infusion System, using Infusion Smithing Templates found in end cities to add corruption or corruption resistance to ANY armor or weapon [ElocinDev]
- Added new model for The Faceless [Mim1q]
- Added new model for Ominous Eyes & dynamic rotation [Mim1q]
- Added recipe for abysmal and eldritch pedestals [ElocinDev]
- Added summoning for The Faceless, done in a ritual with an eldritch pedestal surrounded of abysmal pedestals, with 4 lit black candles on top of each abysmal pedestal, use an aberration heart on top of the eldritch pedestal to summon The Faceless.[ElocinDev]
- Renamed Xal'arath to "X'al" [ElocinDev]
- X'al is implemented now: the Stormsurge ability from The Faceless is casted on interact [ElocinDev]
- Etyrite armor now gives flat +5 corruption resistance per piece [ElocinDev]
- Fixed faceless texture covering the health bar in some parts [ElocinDev]
- Fixed crash caused by FTB Ultimine due to missing chances in the primordial leaves' loot table [ElocinDev]
- Corruption config now uses JSON5 format. Please update your config! [ElocinDev]
- Overhauled Corruption Config, included configs for the corruption system's effects [ElocinDev]
- The corruption icon now has a blinking animation [ElocinDev]
- Added new textures for etyr by @lopyluna [lopyluna]

## 0.2.30 (Public Release)
- Fixed dendler not spawning properly in the hasturian wastes [ElocinDev]
- Added config to enable or disable primordial abyss' midland biome generation [ElocinDev]
- Added new variations of trees [ElocinDev, Wendu]
- Changed Primordial Abyss' default weight from 1.5 to 4.0 [ElocinDev]

## 0.2.20 (Public Release)
RELOAD YOUR HASTURIAN WASTES CONFIG TO BE ABLE TO CHANGE THE DENDLER SPAWN!

- Added Abysmal Pedestal
- Added Eldritch Pedestal
- Added Dendler
- Dendlers now naturally spawn in the hasturian wastes
- Added config for dendlers natural spawning in biomes/hasturian_wastes.json
- Added a better walking animation for the dendler

## 0.2.13 (Acolyte Release)
- Added Dendler
- Dendlers now naturally spawn in the hasturian wastes
- Added config for dendlers natural spawning in biomes/hasturian_wastes.json

## 0.2.12
- Fixed smithing template recipe
- Fixed crash on server

## 0.2.0

- Added Corruption GUI in survival inventory
- Corruption and Corruption Resistance (Labeled as Etyr %) are now attributes
- The player corruption GUI will show the percentage of etyr and total corruption
- Overhauled generation of the biomes, they now feel more natural.
- Added Corruption mechanic config with various entries, not all of them are implemented.
- Added Corruption API under package elocindev.eldritch_end.api.CorruptionAPI
- Fixed black ashes spawning in the hasturian wastes or other biomes
- Fixed primordial trees generating in the hasturian wastes
- Fixed undead tentacle's translation key
- More stuff that i forgot to add because it was before the creation of this file so look at the commits
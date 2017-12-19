# LoRa Server Yocto files

This repository contains recipes for building LoRa Server related packages.

## `layers/meta-loraserver`

This layer contains the build recipes for LoRa Server projects. Note that
these recipes currently fetch the pre-compiled ARM binary.

## `layers/meta-semtech`

This layer contains the build recipes of the Semtech gateway HAL, utilities
and packet-forwarder.

Some of the recipe sources (e.g. patches) are based on the Multitech mLinux
recipes: http://git.multitech.net/cgi-bin/cgit.cgi/meta-mlinux.git/tree/recipes-connectivity/lora.

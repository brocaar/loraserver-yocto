DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5301050fd7cd58850085239d559297be"
SRC_URI = "https://artifacts.loraserver.io/downloads/lora-gateway-bridge/lora-gateway-bridge_${PV}_linux_armv5.tar.gz \
           file://lora-gateway-bridge.toml \
           file://lora-gateway-bridge.init \
"
SRC_URI[md5sum] = "8138b6fe2891f239c8fee96da0d9a5bc"
SRC_URI[sha256sum] = "d85b70e95be1170f765c3a9c4eeb6671afe5a17d27724125602082671ecc0601"
PR = "r1"

LORA_GATEWAY_BRIDGE_DIR = "/opt/lora-gateway-bridge"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${LORA_GATEWAY_BRIDGE_DIR}
    install -m 755 lora-gateway-bridge ${D}${LORA_GATEWAY_BRIDGE_DIR}/

    install -d ${D}/var/config/lora-gateway-bridge
    install -m 0640 ${WORKDIR}/lora-gateway-bridge.toml ${D}/var/config/lora-gateway-bridge/lora-gateway-bridge.toml

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lora-gateway-bridge.init ${D}${sysconfdir}/init.d/lora-gateway-bridge
}

FILES_${PN} += "${LORA_GATEWAY_BRIDGE_DIR}"
FILES_${PN}-dbg += "${LORA_GATEWAY_BRIDGE_DIR}/.debug"

CONFFILES_${PN} += "/var/config/lora-gateway-bridge/lora-gateway-bridge.toml"

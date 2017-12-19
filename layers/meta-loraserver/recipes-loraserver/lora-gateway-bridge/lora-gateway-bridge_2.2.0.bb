DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "https://dl.loraserver.io/tar/lora-gateway-bridge_${PV}_linux_arm.tar.gz \
           file://lora-gateway-bridge.default \
           file://lora-gateway-bridge.init \
"
SRC_URI[md5sum] = "6712cd9a0e9e0906468cfb3100b5c803"
SRC_URI[sha256sum] = "0b8e72fcb7d79c665c4ff4dcf0e6f0c43ec8dfa89f3250daefe0dccb7af51a37"
PR = "r2"

LORA_GATEWAY_BRIDGE_DIR = "/opt/lora-gateway-bridge"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${LORA_GATEWAY_BRIDGE_DIR}
    install -m 755 lora-gateway-bridge ${D}${LORA_GATEWAY_BRIDGE_DIR}/

    install -d ${D}/var/config/lora-gateway-bridge
    install -m 0640 ${WORKDIR}/lora-gateway-bridge.default ${D}/var/config/lora-gateway-bridge/lora-gateway-bridge

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lora-gateway-bridge.init ${D}${sysconfdir}/init.d/lora-gateway-bridge
}

FILES_${PN} += "${LORA_GATEWAY_BRIDGE_DIR}"
FILES_${PN}-dbg += "${LORA_GATEWAY_BRIDGE_DIR}/.debug"

CONFFILES_${PN} += "${sysconfdir}/default/lora-gateway-bridge"

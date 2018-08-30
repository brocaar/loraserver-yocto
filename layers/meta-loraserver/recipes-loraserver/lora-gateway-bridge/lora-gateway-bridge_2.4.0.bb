DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5301050fd7cd58850085239d559297be"
SRC_URI = "https://dl.loraserver.io/tar/lora-gateway-bridge_${PV}_linux_armv5.tar.gz \
           file://lora-gateway-bridge.toml \
           file://lora-gateway-bridge.init \
"
SRC_URI[md5sum] = "5bf046efaabc996853646a993570bbe7"
SRC_URI[sha256sum] = "e66ecb80ed0677b275465a3e4c532443b49265aa845d7cedcabd05b4503a24cd"
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

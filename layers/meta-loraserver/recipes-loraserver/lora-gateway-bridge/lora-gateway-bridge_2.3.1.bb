DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "https://dl.loraserver.io/tar/lora-gateway-bridge_${PV}_linux_arm.tar.gz \
           file://lora-gateway-bridge.toml \
           file://lora-gateway-bridge.init \
"
SRC_URI[md5sum] = "88be8561b0070d21400d56f13981b274"
SRC_URI[sha256sum] = "8699b0730bd1aafed43b995cc6194669b70016b530e3da8d8ba4ef2e703d7317"
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

DESCRIPTION = "LoRa Packet Forwarder"
HOMEPAGE = "https://github.com/lora-net/packet_forwarder"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22af7693d7b76ef0fc76161c4be76c45"
DEPENDS = "lora-gateway"
PR = "r5"

SRCREV = "v${PV}"
SRC_URI = "git://github.com/Lora-net/packet_forwarder.git;protocol=git \
           file://lora-packet-forwarder-ap1.init \
           file://lora-packet-forwarder-ap2.init \
           file://global_conf.json.MTAC-LORA-H-868.basic \
           file://global_conf.json.MTAC-LORA-H-868.gps \
           file://global_conf.json.MTAC-LORA-H-915.basic \
           file://global_conf.json.MTAC-LORA-H-915.gps \
           file://lora-packet-forwarder-add-spi-dev-path.patch \
"

S = "${WORKDIR}/git"
LORA_DIR = "/opt/lora-packet-forwarder"

export LGW_PATH = "${STAGING_LIBDIR}/lora"
export LGW_INC = "${STAGING_INCDIR}/lora"
CFLAGS += "-I${STAGING_INCDIR}/lora -Iinc -I."

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${LORA_DIR}
    install -d ${D}${LORA_DIR}/utils

    install -m 0755 lora_pkt_fwd/lora_pkt_fwd ${D}${LORA_DIR}/

    install -m 0755 util_sink/util_sink ${D}${LORA_DIR}/utils/
    install -m 0755 util_ack/util_ack ${D}${LORA_DIR}/utils/
    install -m 0755 util_tx_test/util_tx_test ${D}${LORA_DIR}/utils/
}

do_install_append_mtcdt() {
    install -d ${D}${LORA_DIR}/config
    install -m 0644 ${WORKDIR}/global_conf.json.* ${D}${LORA_DIR}/config/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lora-packet-forwarder-ap1.init ${D}${sysconfdir}/init.d/lora-packet-forwarder-ap1
    install -m 0755 ${WORKDIR}/lora-packet-forwarder-ap2.init ${D}${sysconfdir}/init.d/lora-packet-forwarder-ap2
}

FILES_${PN} += "${LORA_DIR}"
FILES_${PN}-dbg += "${LORA_DIR}/.debug ${LORA_DIR}/utils/.debug"

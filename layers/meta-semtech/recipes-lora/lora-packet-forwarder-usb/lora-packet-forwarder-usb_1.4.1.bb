DESCRIPTION = "LoRa Packet Forwarder"
HOMEPAGE = "https://github.com/lora-net/packet_forwarder"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=22af7693d7b76ef0fc76161c4be76c45"
DEPENDS = "lora-gateway-usb"
PR = "r2"

# tag v1.4.1
SRCREV = "0011a60759a7d81656a5393e97089daab1ff1a81"

SRC_URI = "git://github.com/lora-net/packet_forwarder.git;protocol=git \
           file://lora-packet-forwarder-usb.init \
           file://global_conf.json.MTAC-LORA-868 \
           file://global_conf.json.MTAC-LORA-915 \
           file://lora-packet-forwarder-add-no-header-option.patch \
           file://lora-packet-forwarder-set-spi-path.patch \
           file://lora-packet-forwarder-skip-bad-packets.patch \
           file://lora-packet-forwarder-fixb64.patch \
           file://lora-packet-forwarder-mts-enhancements.patch \
           file://lora-packet-forwarder-synch-word.patch \
           file://lora-packet-forwarder-add-queue.patch \
           file://README.md \
"

S = "${WORKDIR}/git"
LORA_DIR = "/opt/lora-packet-forwarder-usb"

export LGW_PATH = "${STAGING_LIBDIR}/lora-usb"
export LGW_INC = "${STAGING_INCDIR}/lora-usb"
CFLAGS += "-I${LGW_INC} -Iinc -I."

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}${LORA_DIR}
	install -m 755 gps_pkt_fwd/gps_pkt_fwd ${D}${LORA_DIR}/gps_pkt_fwd
	install -m 755 basic_pkt_fwd/basic_pkt_fwd ${D}${LORA_DIR}/basic_pkt_fwd
	install -m 755 beacon_pkt_fwd/beacon_pkt_fwd ${D}${LORA_DIR}/beacon_pkt_fwd
	install -m 755 util_sink/util_sink ${D}${LORA_DIR}/util_sink
	install -m 755 util_ack/util_ack ${D}${LORA_DIR}/util_ack
}

do_install_append_mtcdt() {
    install -d ${D}${LORA_DIR}/config
    install -m 0644 ${WORKDIR}/global_conf.json.* ${D}${LORA_DIR}/config/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lora-packet-forwarder-usb.init ${D}${sysconfdir}/init.d/lora-packet-forwarder-usb
}

FILES_${PN} += "${LORA_DIR}"
FILES_${PN}-dbg += "${LORA_DIR}/.debug"

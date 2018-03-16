DESCRIPTION = "Driver/HAL to build a gateway using a concentrator board based on Semtech SX1301 (FTDI)"
HOMEPAGE = "https://github.com/Lora-net/lora_gateway"
PRIORITY = "optional"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a2bdef95625509f821ba00460e3ae0eb"
DEPENDS = "libftdi libmpsse libusb1"
PR = "r2"
BRANCH = "1.7.0-mts"
SRCREV = "631dfedf1b535b6c3861d3c3868e02f48a84fb06"

SRC_URI = "git://git.multitech.net/lora_gateway;protocol=git;branch=${BRANCH} \
           file://lora-gateway-debug.patch \
           file://lora-gateway-sync-word.patch \
           file://library.cfg \
          "

S = "${WORKDIR}/git"
CFLAGS += "-Iinc -I. -DLIBFTDI1=1"

do_configure_append() {
    cp ${WORKDIR}/library.cfg ${S}/libloragw/library.cfg
}

do_compile() {
    oe_runmake
}

do_install() {
	install -d ${D}${libdir}/lora-usb
	install -d ${D}${includedir}/lora-usb

	install -m 0644 libloragw/libloragw.a ${D}${libdir}/lora-usb
	install -m 0644 libloragw/library.cfg ${D}${libdir}/lora-usb
	install -m 0644 libloragw/inc/* ${D}${includedir}/lora-usb

	install -d ${D}/opt/lora-gateway-usb/gateway-utils
	install -m 0755 util_pkt_logger/util_pkt_logger ${D}/opt/lora-gateway-usb/gateway-utils/util_pkt_logger
	install -m 0755 util_band_survey/util_band_survey ${D}/opt/lora-gateway-usb/gateway-utils/util_band_survey
	install -m 0755 util_spi_stress/util_spi_stress ${D}/opt/lora-gateway-usb/gateway-utils/util_spi_stress-usb
	install -m 0755 util_tx_test/util_tx_test ${D}/opt/lora-gateway-usb/gateway-utils/util_tx_test-usb
}

PACKAGES += "${PN}-utils ${PN}-utils-dbg"

FILES_${PN}-staticdev = "${libdir}/lora-usb/libloragw.a"
FILES_${PN}-utils = "/opt/lora-gateway-usb/gateway-utils/*"
FILES_${PN}-utils-dbg = "/opt/lora/.debug"
FILES_${PN}-dev = "${includedir}/lora-usb ${libdir}/lora-usb/library.cfg"

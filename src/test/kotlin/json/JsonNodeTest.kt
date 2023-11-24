package json

import com.fasterxml.jackson.databind.JsonNode
import org.junit.jupiter.api.Test

class JsonNodeTest {
    @Test
    fun testJsonNode() {
        var stout = """
{
    "ip_addr": [
        {
            "addr_info": [
                {
                    "family": "inet",
                    "valid_life_time": 4294967295,
                    "label": "lo",
                    "preferred_life_time": 4294967295,
                    "prefixlen": 8,
                    "scope": "host",
                    "local": "127.0.0.1"
                },
                {
                    "family": "inet6",
                    "valid_life_time": 4294967295,
                    "preferred_life_time": 4294967295,
                    "prefixlen": 128,
                    "scope": "host",
                    "local": "::1"
                }
            ],
            "operstate": "UNKNOWN",
            "qdisc": "noqueue",
            "group": "default",
            "mtu": 65536,
            "broadcast": "00:00:00:00:00:00",
            "flags": [
                "LOOPBACK",
                "UP",
                "LOWER_UP"
            ],
            "address": "00:00:00:00:00:00",
            "ifindex": 1,
            "txqlen": 1000,
            "ifname": "lo",
            "link_type": "loopback"
        },
        {
            "addr_info": [
                {
                    "family": "inet",
                    "valid_life_time": 4294967295,
                    "label": "eth0",
                    "broadcast": "10.211.55.255",
                    "preferred_life_time": 4294967295,
                    "prefixlen": 24,
                    "scope": "global",
                    "local": "10.211.55.15"
                },
                {
                    "family": "inet6",
                    "valid_life_time": 4294967295,
                    "preferred_life_time": 4294967295,
                    "prefixlen": 64,
                    "scope": "link",
                    "local": "fe80::21c:42ff:fe86:25e0"
                }
            ],
            "operstate": "UP",
            "qdisc": "pfifo_fast",
            "group": "default",
            "mtu": 1500,
            "broadcast": "ff:ff:ff:ff:ff:ff",
            "flags": [
                "BROADCAST",
                "MULTICAST",
                "UP",
                "LOWER_UP"
            ],
            "address": "00:1c:42:86:25:e0",
            "ifindex": 2,
            "txqlen": 1000,
            "ifname": "eth0",
            "link_type": "ether"
        },
        {
            "addr_info": [
                {
                    "family": "inet",
                    "valid_life_time": 4294967295,
                    "label": "docker0",
                    "broadcast": "172.17.255.255",
                    "preferred_life_time": 4294967295,
                    "prefixlen": 16,
                    "scope": "global",
                    "local": "172.17.0.1"
                }
            ],
            "operstate": "DOWN",
            "qdisc": "noqueue",
            "group": "default",
            "mtu": 1500,
            "broadcast": "ff:ff:ff:ff:ff:ff",
            "flags": [
                "NO-CARRIER",
                "BROADCAST",
                "MULTICAST",
                "UP"
            ],
            "address": "02:42:6b:eb:c4:d0",
            "ifindex": 3,
            "ifname": "docker0",
            "link_type": "ether"
        }
    ],
    "lshw": {
        "product": "Parallels Virtual Platform (Undefined)",
        "handle": "DMI:0001",
        "description": "Computer",
        "children": [
            {
                "product": "Parallels Virtual Platform",
                "handle": "DMI:0002",
                "description": "Motherboard",
                "children": [
                    {
                        "units": "bytes",
                        "vendor": "Parallels Software International Inc.",
                        "description": "BIOS",
                        "capabilities": {
                            "int10video": "INT10 CGA/Mono video",
                            "int17printer": "INT17 printer control",
                            "virtualmachine": "This machine is a virtual machine",
                            "int13floppy720": "3.5\" 720KB floppy",
                            "pnp": "Plug-and-Play",
                            "int9keyboard": "i8042 keyboard controller",
                            "int13floppy2880": "3.5\" 2.88MB floppy",
                            "pci": "PCI bus",
                            "apm": "Advanced Power Management",
                            "isa": "ISA bus",
                            "int14serial": "INT14 serial line control",
                            "acpi": "ACPI",
                            "cdboot": "Booting from CD-ROM/DVD"
                        },
                        "class": "memory",
                        "date": "12/18/2020",
                        "version": "16.1.2 (49151)",
                        "claimed": true,
                        "physid": "0",
                        "id": "firmware",
                        "size": 65536
                    },
                    {
                        "slot": "CPU Socket #0",
                        "product": "Intel(R) Core(TM) i9-9980HK CPU @ 2.40GHz",
                        "capacity": 2400000000,
                        "handle": "DMI:0004",
                        "description": "CPU",
                        "width": 64,
                        "clock": 100000000,
                        "capabilities": {
                            "pge": "page global enable",
                            "avx": true,
                            "xsaveopt": true,
                            "clflush": true,
                            "sep": "fast system calls",
                            "syscall": "fast system calls",
                            "tsc_deadline_timer": true,
                            "vme": "virtual mode extensions",
                            "invpcid": true,
                            "msr": "model-specific registers",
                            "fsgsbase": true,
                            "xsave": true,
                            "smap": true,
                            "fpu_exception": "FPU exceptions reporting",
                            "xtopology": true,
                            "pln": true,
                            "cmov": "conditional move instruction",
                            "pts": true,
                            "smep": true,
                            "nx": "no-execute bit (NX)",
                            "clflushopt": true,
                            "constant_tsc": true,
                            "pat": "page attribute table",
                            "eagerfpu": true,
                            "tsc": "time stamp counter",
                            "adx": true,
                            "3dnowprefetch": true,
                            "fpu": "mathematical co-processor",
                            "fxsr": "fast floating point save/restore",
                            "pae": "4GB+ memory addressing (Physical Address Extension)",
                            "pcid": true,
                            "nopl": true,
                            "mmx": "multimedia extensions (MMX)",
                            "arat": true,
                            "cx8": "compare and exchange 8-byte",
                            "nonstop_tsc": true,
                            "mce": "machine check exceptions",
                            "de": "debugging extensions",
                            "pclmulqdq": true,
                            "mca": "machine check architecture",
                            "pse": "page size extensions",
                            "pni": true,
                            "abm": true,
                            "tsc_adjust": true,
                            "x86-64": "64bits extensions (x86-64)",
                            "popcnt": true,
                            "ht": "HyperThreading",
                            "apic": "on-chip advanced programmable interrupt controller (APIC)",
                            "sse": "streaming SIMD extensions (SSE)",
                            "sse4_2": true,
                            "f16c": true,
                            "dtherm": true,
                            "wp": true,
                            "xsavec": true,
                            "lahf_lm": true,
                            "rdtscp": true,
                            "avx2": true,
                            "aes": true,
                            "sse2": "streaming SIMD extensions (SSE2)",
                            "ss": "self-snoop",
                            "hypervisor": true,
                            "sse4_1": true,
                            "bmi1": true,
                            "bmi2": true,
                            "ssse3": true,
                            "rdseed": true,
                            "cx16": true,
                            "pse36": "36-bit page size extensions",
                            "mtrr": "memory type range registers",
                            "movbe": true,
                            "rdrand": true,
                            "fma": true,
                            "x2apic": true
                        },
                        "class": "processor",
                        "units": "Hz",
                        "businfo": "cpu@0",
                        "physid": "4",
                        "claimed": true,
                        "vendor": "Intel Corp.",
                        "id": "cpu",
                        "size": 2400000000
                    },
                    {
                        "slot": "System board or motherboard",
                        "handle": "DMI:000C",
                        "description": "System Memory",
                        "children": [
                            {
                                "slot": "DIMM #0",
                                "handle": "DMI:000D",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns)",
                                "width": 32,
                                "clock": 667000000,
                                "class": "memory",
                                "units": "bytes",
                                "claimed": true,
                                "physid": "0",
                                "id": "bank:0",
                                "size": 8589934592
                            },
                            {
                                "slot": "DIMM #1",
                                "handle": "DMI:000E",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns) [empty]",
                                "clock": 667000000,
                                "class": "memory",
                                "width": 32,
                                "claimed": true,
                                "physid": "1",
                                "id": "bank:1"
                            },
                            {
                                "slot": "DIMM #2",
                                "handle": "DMI:000F",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns) [empty]",
                                "clock": 667000000,
                                "class": "memory",
                                "width": 32,
                                "claimed": true,
                                "physid": "2",
                                "id": "bank:2"
                            },
                            {
                                "slot": "DIMM #3",
                                "handle": "DMI:0010",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns) [empty]",
                                "clock": 667000000,
                                "class": "memory",
                                "width": 32,
                                "claimed": true,
                                "physid": "3",
                                "id": "bank:3"
                            },
                            {
                                "slot": "DIMM #4",
                                "handle": "DMI:0011",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns) [empty]",
                                "clock": 667000000,
                                "class": "memory",
                                "width": 32,
                                "claimed": true,
                                "physid": "4",
                                "id": "bank:4"
                            },
                            {
                                "slot": "DIMM #5",
                                "handle": "DMI:0012",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns) [empty]",
                                "clock": 667000000,
                                "class": "memory",
                                "width": 32,
                                "claimed": true,
                                "physid": "5",
                                "id": "bank:5"
                            },
                            {
                                "slot": "DIMM #6",
                                "handle": "DMI:0013",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns) [empty]",
                                "clock": 667000000,
                                "class": "memory",
                                "width": 32,
                                "claimed": true,
                                "physid": "6",
                                "id": "bank:6"
                            },
                            {
                                "slot": "DIMM #7",
                                "handle": "DMI:0014",
                                "description": "DIMM DRAM EDO 667 MHz (1.5 ns) [empty]",
                                "clock": 667000000,
                                "class": "memory",
                                "width": 32,
                                "claimed": true,
                                "physid": "7",
                                "id": "bank:7"
                            }
                        ],
                        "class": "memory",
                        "units": "bytes",
                        "claimed": true,
                        "physid": "c",
                        "id": "memory",
                        "size": 8589934592
                    },
                    {
                        "product": "82P965/G965 Memory Controller Hub",
                        "handle": "PCIBUS:0000:00",
                        "description": "Host bridge",
                        "clock": 33000000,
                        "children": [
                            {
                                "product": "82G35 Express PCI Express Root Port",
                                "handle": "PCIBUS:0000:01",
                                "description": "PCI bridge",
                                "clock": 33000000,
                                "children": [
                                    {
                                        "product": "Virtio GPU",
                                        "handle": "PCI:0000:01:00.0",
                                        "description": "VGA compatible controller",
                                        "clock": 33000000,
                                        "children": [
                                            {
                                                "description": "Virtual I/O device",
                                                "class": "generic",
                                                "id": "virtio2",
                                                "physid": "0",
                                                "configuration": {
                                                    "driver": "virtio_gpu"
                                                },
                                                "businfo": "virtio@2"
                                            }
                                        ],
                                        "capabilities": {
                                            "msix": "MSI-X",
                                            "rom": "extension ROM",
                                            "vga_controller": true,
                                            "bus_master": "bus mastering",
                                            "cap_list": "PCI capabilities listing",
                                            "msi": "Message Signalled Interrupts"
                                        },
                                        "class": "display",
                                        "width": 32,
                                        "version": "01",
                                        "businfo": "pci@0000:01:00.0",
                                        "physid": "0",
                                        "claimed": true,
                                        "vendor": "Red Hat, Inc.",
                                        "configuration": {
                                            "latency": "0",
                                            "driver": "virtio-pci"
                                        },
                                        "id": "display"
                                    }
                                ],
                                "capabilities": {
                                    "cap_list": "PCI capabilities listing",
                                    "msi": "Message Signalled Interrupts",
                                    "bus_master": "bus mastering",
                                    "subtractive_decode": true,
                                    "pci": true,
                                    "pciexpress": "PCI Express",
                                    "pm": "Power Management"
                                },
                                "class": "bridge",
                                "width": 32,
                                "version": "02",
                                "businfo": "pci@0000:00:01.0",
                                "physid": "1",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "id": "pci:0"
                            },
                            {
                                "product": "Virtual Machine Communication Interface",
                                "vendor": "Parallels, Inc.",
                                "description": "Unassigned class",
                                "clock": 33000000,
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "cap_list": "PCI capabilities listing",
                                    "msi": "Message Signalled Interrupts"
                                },
                                "class": "generic",
                                "width": 32,
                                "version": "00",
                                "businfo": "pci@0000:00:03.0",
                                "physid": "3",
                                "handle": "PCI:0000:00:03.0",
                                "configuration": {
                                    "latency": "0"
                                },
                                "id": "generic"
                            },
                            {
                                "product": "Virtio network device",
                                "handle": "PCI:0000:00:05.0",
                                "description": "Ethernet controller",
                                "clock": 33000000,
                                "children": [
                                    {
                                        "description": "Ethernet interface",
                                        "class": "network",
                                        "logicalname": "eth0",
                                        "capabilities": {
                                            "ethernet": true,
                                            "logical": "Logical interface",
                                            "physical": "Physical interface"
                                        },
                                        "id": "virtio0",
                                        "claimed": true,
                                        "physid": "0",
                                        "serial": "00:1c:42:86:25:e0",
                                        "configuration": {
                                            "ip": "10.211.55.15",
                                            "driverversion": "1.0.0",
                                            "driver": "virtio_net",
                                            "broadcast": "yes",
                                            "multicast": "yes",
                                            "link": "yes"
                                        },
                                        "businfo": "virtio@0"
                                    }
                                ],
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "msix": "MSI-X",
                                    "cap_list": "PCI capabilities listing",
                                    "pciexpress": "PCI Express",
                                    "pm": "Power Management"
                                },
                                "class": "network",
                                "width": 32,
                                "version": "00",
                                "businfo": "pci@0000:00:05.0",
                                "physid": "5",
                                "claimed": true,
                                "vendor": "Red Hat, Inc.",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "virtio-pci"
                                },
                                "id": "network"
                            },
                            {
                                "product": "DECchip 21150",
                                "handle": "PCIBUS:0000:02",
                                "description": "PCI bridge",
                                "clock": 33000000,
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "pci": true,
                                    "normal_decode": true
                                },
                                "class": "bridge",
                                "width": 32,
                                "version": "00",
                                "businfo": "pci@0000:00:0a.0",
                                "physid": "a",
                                "claimed": true,
                                "vendor": "Digital Equipment Corporation",
                                "id": "pci:1"
                            },
                            {
                                "product": "Virtio memory balloon",
                                "handle": "PCI:0000:00:0e.0",
                                "description": "RAM memory",
                                "clock": 33000000,
                                "children": [
                                    {
                                        "description": "Virtual I/O device",
                                        "class": "generic",
                                        "id": "virtio1",
                                        "physid": "0",
                                        "configuration": {
                                            "driver": "virtio_balloon"
                                        },
                                        "businfo": "virtio@1"
                                    }
                                ],
                                "capabilities": {
                                    "bus_master": "bus mastering"
                                },
                                "class": "memory",
                                "width": 32,
                                "version": "00",
                                "businfo": "pci@0000:00:0e.0",
                                "physid": "e",
                                "claimed": true,
                                "vendor": "Red Hat, Inc.",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "virtio-pci"
                                },
                                "id": "memory"
                            },
                            {
                                "product": "82801FB/FBM/FR/FW/FRW (ICH6 Family) USB UHCI #1",
                                "handle": "PCI:0000:00:1d.0",
                                "description": "USB controller",
                                "clock": 33000000,
                                "children": [
                                    {
                                        "product": "UHCI Host Controller",
                                        "vendor": "Linux 3.10.0-957.27.2.el7.x86_64 uhci_hcd",
                                        "logicalname": "usb2",
                                        "children": [
                                            {
                                                "product": "Virtual Mouse",
                                                "handle": "USB:2:2",
                                                "description": "Mouse",
                                                "capabilities": {
                                                    "usb-1.10": "USB 1.1"
                                                },
                                                "class": "input",
                                                "version": "1.00",
                                                "businfo": "usb@2:1",
                                                "physid": "1",
                                                "claimed": true,
                                                "vendor": "Parallels",
                                                "serial": "PW3.0",
                                                "configuration": {
                                                    "speed": "12Mbit/s",
                                                    "driver": "usbhid"
                                                },
                                                "id": "usb"
                                            }
                                        ],
                                        "capabilities": {
                                            "usb-1.10": "USB 1.1"
                                        },
                                        "class": "bus",
                                        "version": "3.10",
                                        "businfo": "usb@2",
                                        "physid": "1",
                                        "claimed": true,
                                        "handle": "USB:2:1",
                                        "configuration": {
                                            "speed": "12Mbit/s",
                                            "slots": "2",
                                            "driver": "hub"
                                        },
                                        "id": "usbhost"
                                    }
                                ],
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "uhci": "Universal Host Controller Interface (USB1)"
                                },
                                "class": "bus",
                                "width": 32,
                                "version": "02",
                                "businfo": "pci@0000:00:1d.0",
                                "physid": "1d",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "uhci_hcd"
                                },
                                "id": "usb:0"
                            },
                            {
                                "product": "uPD720200 USB 3.0 Host Controller",
                                "handle": "PCI:0000:00:1d.6",
                                "description": "USB controller",
                                "clock": 33000000,
                                "children": [
                                    {
                                        "product": "xHCI Host Controller",
                                        "vendor": "Linux 3.10.0-957.27.2.el7.x86_64 xhci-hcd",
                                        "logicalname": "usb3",
                                        "capabilities": {
                                            "usb-2.00": "USB 2.0"
                                        },
                                        "class": "bus",
                                        "version": "3.10",
                                        "businfo": "usb@3",
                                        "physid": "0",
                                        "claimed": true,
                                        "handle": "USB:3:1",
                                        "configuration": {
                                            "speed": "480Mbit/s",
                                            "slots": "2",
                                            "driver": "hub"
                                        },
                                        "id": "usbhost:0"
                                    },
                                    {
                                        "product": "xHCI Host Controller",
                                        "vendor": "Linux 3.10.0-957.27.2.el7.x86_64 xhci-hcd",
                                        "logicalname": "usb4",
                                        "capabilities": {
                                            "usb-3.00": true
                                        },
                                        "class": "bus",
                                        "version": "3.10",
                                        "businfo": "usb@4",
                                        "physid": "1",
                                        "claimed": true,
                                        "handle": "USB:4:1",
                                        "configuration": {
                                            "speed": "5000Mbit/s",
                                            "slots": "12",
                                            "driver": "hub"
                                        },
                                        "id": "usbhost:1"
                                    }
                                ],
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "cap_list": "PCI capabilities listing",
                                    "xhci": true,
                                    "msi": "Message Signalled Interrupts"
                                },
                                "class": "bus",
                                "width": 32,
                                "version": "04",
                                "businfo": "pci@0000:00:1d.6",
                                "physid": "1d.6",
                                "claimed": true,
                                "vendor": "NEC Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "xhci_hcd"
                                },
                                "id": "usb:1"
                            },
                            {
                                "product": "82801FB/FBM/FR/FW/FRW (ICH6 Family) USB2 EHCI Controller",
                                "handle": "PCI:0000:00:1d.7",
                                "description": "USB controller",
                                "clock": 33000000,
                                "children": [
                                    {
                                        "product": "EHCI Host Controller",
                                        "vendor": "Linux 3.10.0-957.27.2.el7.x86_64 ehci_hcd",
                                        "logicalname": "usb1",
                                        "capabilities": {
                                            "usb-2.00": "USB 2.0"
                                        },
                                        "class": "bus",
                                        "version": "3.10",
                                        "businfo": "usb@1",
                                        "physid": "1",
                                        "claimed": true,
                                        "handle": "USB:1:1",
                                        "configuration": {
                                            "speed": "480Mbit/s",
                                            "slots": "15",
                                            "driver": "hub"
                                        },
                                        "id": "usbhost"
                                    }
                                ],
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "ehci": "Enhanced Host Controller Interface (USB2)"
                                },
                                "class": "bus",
                                "width": 32,
                                "version": "02",
                                "businfo": "pci@0000:00:1d.7",
                                "physid": "1d.7",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "ehci-pci"
                                },
                                "id": "usb:2"
                            },
                            {
                                "product": "82801 PCI Bridge",
                                "handle": "PCIBUS:0000:03",
                                "description": "PCI bridge",
                                "clock": 33000000,
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "pci": true,
                                    "cap_list": "PCI capabilities listing",
                                    "normal_decode": true
                                },
                                "class": "bridge",
                                "width": 32,
                                "version": "f2",
                                "businfo": "pci@0000:00:1e.0",
                                "physid": "1e",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "id": "pci:2"
                            },
                            {
                                "product": "82801HB/HR (ICH8/R) LPC Interface Controller",
                                "handle": "PCI:0000:00:1f.0",
                                "description": "ISA bridge",
                                "clock": 33000000,
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "isa": true,
                                    "cap_list": "PCI capabilities listing"
                                },
                                "class": "bridge",
                                "width": 32,
                                "version": "02",
                                "businfo": "pci@0000:00:1f.0",
                                "physid": "1f",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "lpc_ich"
                                },
                                "id": "isa"
                            },
                            {
                                "product": "82801BA IDE U100 Controller",
                                "handle": "PCI:0000:00:1f.1",
                                "description": "IDE interface",
                                "clock": 33000000,
                                "capabilities": {
                                    "cap_list": "PCI capabilities listing",
                                    "bus_master": "bus mastering",
                                    "pciexpress": "PCI Express",
                                    "ide": true,
                                    "isa_compatibility_mode-only_controller__supports_bus_mastering": true,
                                    "pm": "Power Management"
                                },
                                "class": "storage",
                                "width": 32,
                                "version": "05",
                                "businfo": "pci@0000:00:1f.1",
                                "physid": "1f.1",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "ata_piix"
                                },
                                "id": "ide"
                            },
                            {
                                "product": "82801HR/HO/HH (ICH8R/DO/DH) 6 port SATA Controller [AHCI mode]",
                                "handle": "PCI:0000:00:1f.2",
                                "description": "SATA controller",
                                "clock": 33000000,
                                "capabilities": {
                                    "cap_list": "PCI capabilities listing",
                                    "pciexpress": "PCI Express",
                                    "ahci_1.0": true,
                                    "storage": true,
                                    "msi": "Message Signalled Interrupts",
                                    "pm": "Power Management"
                                },
                                "class": "storage",
                                "width": 32,
                                "version": "02",
                                "businfo": "pci@0000:00:1f.2",
                                "physid": "1f.2",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "ahci"
                                },
                                "id": "storage"
                            },
                            {
                                "product": "82801H (ICH8 Family) SMBus Controller",
                                "handle": "PCI:0000:00:1f.3",
                                "description": "SMBus",
                                "clock": 33000000,
                                "class": "bus",
                                "width": 32,
                                "version": "02",
                                "businfo": "pci@0000:00:1f.3",
                                "physid": "1f.3",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "i801_smbus"
                                },
                                "id": "serial"
                            },
                            {
                                "product": "82801I (ICH9 Family) HD Audio Controller",
                                "handle": "PCI:0000:00:1f.4",
                                "description": "Audio device",
                                "clock": 33000000,
                                "capabilities": {
                                    "bus_master": "bus mastering",
                                    "cap_list": "PCI capabilities listing",
                                    "msi": "Message Signalled Interrupts",
                                    "pciexpress": "PCI Express"
                                },
                                "class": "multimedia",
                                "width": 32,
                                "version": "00",
                                "businfo": "pci@0000:00:1f.4",
                                "physid": "1f.4",
                                "claimed": true,
                                "vendor": "Intel Corporation",
                                "configuration": {
                                    "latency": "0",
                                    "driver": "snd_hda_intel"
                                },
                                "id": "multimedia"
                            }
                        ],
                        "class": "bridge",
                        "width": 32,
                        "version": "02",
                        "businfo": "pci@0000:00:00.0",
                        "physid": "100",
                        "claimed": true,
                        "vendor": "Intel Corporation",
                        "id": "pci"
                    },
                    {
                        "logicalname": "scsi2",
                        "children": [
                            {
                                "product": "zops-dev-0 SSD",
                                "handle": "SCSI:02:00:00:00",
                                "description": "ATA Disk",
                                "capabilities": {
                                    "partitioned:dos": "MS-DOS partition table",
                                    "partitioned": "Partitioned disk"
                                },
                                "logicalname": "/dev/sda",
                                "children": [
                                    {
                                        "capacity": 1073741824,
                                        "description": "Linux filesystem partition",
                                        "class": "volume",
                                        "capabilities": {
                                            "bootable": "Bootable partition (active)",
                                            "primary": "Primary partition"
                                        },
                                        "id": "volume:0",
                                        "physid": "1",
                                        "businfo": "scsi@2:0.0.0,1"
                                    },
                                    {
                                        "capacity": 67644686336,
                                        "description": "Linux LVM Physical Volume partition",
                                        "class": "volume",
                                        "logicalname": "/dev/sda2",
                                        "dev": "8:2",
                                        "id": "volume:1",
                                        "capabilities": {
                                            "multi": "Multi-volumes",
                                            "primary": "Primary partition",
                                            "lvm2": true
                                        },
                                        "claimed": true,
                                        "physid": "2",
                                        "serial": "d7edc0-4OsY-IoZ9-glQp-6fSu-Wwhk-NRKXGQ",
                                        "businfo": "scsi@2:0.0.0,2",
                                        "size": 67644686336
                                    },
                                    {
                                        "capacity": 68719476736,
                                        "description": "Linux LVM Physical Volume partition",
                                        "class": "volume",
                                        "logicalname": "/dev/sda3",
                                        "dev": "8:3",
                                        "id": "volume:2",
                                        "capabilities": {
                                            "multi": "Multi-volumes",
                                            "primary": "Primary partition",
                                            "lvm2": true
                                        },
                                        "claimed": true,
                                        "physid": "3",
                                        "serial": "sQ3HDx-1QqU-5Idg-k6uS-IY8c-0sMM-3cpEbS",
                                        "businfo": "scsi@2:0.0.0,3",
                                        "size": 68719476736
                                    }
                                ],
                                "dev": "8:0",
                                "class": "disk",
                                "units": "bytes",
                                "version": "RR7G",
                                "businfo": "scsi@2:0.0.0",
                                "claimed": true,
                                "physid": "0.0.0",
                                "serial": "QTNXMCB7R2447AKSWP15",
                                "configuration": {
                                    "ansiversion": "5",
                                    "sectorsize": "4096",
                                    "logicalsectorsize": "512",
                                    "signature": "00091914"
                                },
                                "id": "disk",
                                "size": 137438953472
                            }
                        ],
                        "capabilities": {
                            "emulated": "Emulated device"
                        },
                        "class": "storage",
                        "claimed": true,
                        "physid": "1",
                        "id": "scsi:0"
                    },
                    {
                        "logicalname": "scsi3",
                        "children": [
                            {
                                "product": "Virtual DVD-ROM",
                                "handle": "SCSI:03:00:00:00",
                                "description": "DVD reader",
                                "capabilities": {
                                    "dvd": "DVD playback",
                                    "audio": "Audio CD playback",
                                    "removable": "support is removable"
                                },
                                "logicalname": [
                                    "/dev/cdrom",
                                    "/dev/sr0"
                                ],
                                "dev": "11:0",
                                "class": "disk",
                                "version": "R103",
                                "businfo": "scsi@3:0.0.0",
                                "claimed": true,
                                "physid": "0.0.0",
                                "configuration": {
                                    "ansiversion": "5",
                                    "status": "nodisc"
                                },
                                "id": "cdrom"
                            }
                        ],
                        "capabilities": {
                            "emulated": "Emulated device"
                        },
                        "class": "storage",
                        "claimed": true,
                        "physid": "2",
                        "id": "scsi:1"
                    }
                ],
                "class": "bus",
                "version": "None",
                "physid": "0",
                "claimed": true,
                "vendor": "Parallels Software International Inc.",
                "serial": "None",
                "id": "core"
            },
            {
                "description": "Ethernet interface",
                "logicalname": "docker0",
                "capabilities": {
                    "ethernet": true,
                    "physical": "Physical interface"
                },
                "class": "network",
                "claimed": true,
                "physid": "1",
                "serial": "02:42:6b:eb:c4:d0",
                "configuration": {
                    "firmware": "N/A",
                    "ip": "172.17.0.1",
                    "driverversion": "2.3",
                    "driver": "bridge",
                    "broadcast": "yes",
                    "multicast": "yes",
                    "link": "no"
                },
                "id": "network"
            }
        ],
        "capabilities": {
            "smp": "Symmetric Multi-Processing",
            "vsyscall32": "32-bit processes",
            "dmi-2.7": "DMI version 2.7",
            "smbios-2.7": "SMBIOS version 2.7"
        },
        "class": "system",
        "width": 4294967295,
        "version": "None",
        "claimed": true,
        "vendor": "Parallels Software International Inc.",
        "serial": "Parallels-80 F4 F6 68 9C 1E 46 8B 97 43 DE 3B 81 4D 00 EC",
        "configuration": {
            "sku": "Undefined",
            "boot": "normal",
            "uuid": "80F4F668-9C1E-468B-9743-DE3B814D00EC",
            "family": "Parallels VM"
        },
        "id": "zops-dev"
    },
    "mac_addr": [
        "172.17.0.1|docker0|02:42:6b:eb:c4:d0",
        "10.211.55.15|eth0|00:1c:42:86:25:e0"
    ],
    "ip_link": [
        {
            "operstate": "UNKNOWN",
            "qdisc": "noqueue",
            "group": "default",
            "linkmode": "DEFAULT",
            "mtu": 65536,
            "broadcast": "00:00:00:00:00:00",
            "flags": [
                "LOOPBACK",
                "UP",
                "LOWER_UP"
            ],
            "address": "00:00:00:00:00:00",
            "ifindex": 1,
            "txqlen": 1000,
            "ifname": "lo",
            "link_type": "loopback"
        },
        {
            "operstate": "UP",
            "qdisc": "pfifo_fast",
            "group": "default",
            "linkmode": "DEFAULT",
            "mtu": 1500,
            "broadcast": "ff:ff:ff:ff:ff:ff",
            "flags": [
                "BROADCAST",
                "MULTICAST",
                "UP",
                "LOWER_UP"
            ],
            "address": "00:1c:42:86:25:e0",
            "ifindex": 2,
            "txqlen": 1000,
            "ifname": "eth0",
            "link_type": "ether"
        },
        {
            "operstate": "DOWN",
            "qdisc": "noqueue",
            "group": "default",
            "linkmode": "DEFAULT",
            "mtu": 1500,
            "broadcast": "ff:ff:ff:ff:ff:ff",
            "flags": [
                "NO-CARRIER",
                "BROADCAST",
                "MULTICAST",
                "UP"
            ],
            "address": "02:42:6b:eb:c4:d0",
            "ifindex": 3,
            "ifname": "docker0",
            "link_type": "ether"
        }
    ]
}
        """.trimIndent()

        val node = Coder.jsonReadTree(stout)
        println(Coder.jsonNodeToAny(node.get("mac_addr")))
        println(Coder.jsonNodeToAny(node.at("/mac_addr/1")))

    }
}

fun getMac(node: JsonNode): List<String> {
    val mac = Coder.jsonFind(node) {
        get("mac_addr")?.asText() != null
    }?.get("mac_addr")
    mac?.let {
        return Coder.jsonNodeToAny(it) as List<String>
    }
    return listOf()
}
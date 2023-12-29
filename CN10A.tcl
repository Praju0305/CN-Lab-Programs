#create 
set ns [new Simulator]
set ntrace [open CN10A.tr w]
$ns trace-all $ntrace
set namfile [open CN10A.nam w]
$ns namtrace-all $namfile

#finish procedure
proc Finish {} {
    global ns ntrace namfile
    $ns flush-trace
    close $ntrace
    close $namfile

    exec nam CN10A.nam &
    exec echo "The number of packet drops is " &
    exec grep -c "^d" CN10A.tr &
    exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$n0 label "TCP Source"
$n2 label "Sink"

$ns color 1 Blue

$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail

$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n1 $n2 orient right

$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10

set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n2 $sink
$ns connect $tcp $sink

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $tcp
$cbr set packetSize_ 500
$cbr set class_ 1

$ns at 0.0 "$cbr start"
$ns at 4.0 "Finish"

$ns run
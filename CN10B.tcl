set ns [new Simulator]

set ntrace [open CN10B.tr w]
set namfile [open CN10B.nam w]
$ns namtrace-all $namfile
$ns trace-all $ntrace

proc Finish {} {
    global ns ntrace namfile
    $ns flush-trace
    close $ntrace
    close $namfile

    exec nam CN10B.nam &
    exec echo "The number of ping packets dropped is : " &
    exec grep "^d" CN10B.tr | cut -d " " -f 5 | grep -c "ping" &
    exit 0
}

for { set i 0 } {$i < 6} {incr i} {
    set n($i) [$ns node]
}

for { set i 0 } {$i < 5} {incr i} {
    $ns duplex-link $n($i) $n([expr $i+1]) 0.1Mb 10ms DropTail
}

Agent/Ping instproc recv {from rtt} {
#	$self instvar node_
#	puts "node [$node_ id] received ping answer from $from with round trip time $rtt ms"
}

set p0 [new Agent/Ping]
set p1 [new Agent/Ping]
$ns attach-agent $n(0) $p0
$ns attach-agent $n(5) $p1
$ns connect $p0 $p1

set tcp [new Agent/TCP]
set sink [new Agent/TCPSink]
$ns attach-agent $n(2) $tcp
$ns attach-agent $n(4) $sink
$ns connect $tcp $sink

$ns queue-limit $n(2) $n(3) 2
$ns duplex-link-op $n(2) $n(3) queuePos 0.5

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $tcp
$cbr set packetSize_ 500
$cbr set rate_ 1Mb

$ns at 0.0 "$p0 send"
$ns at 0.2 "$p1 send"
$ns at 1.0 "$cbr start"
$ns at 1.2 "$p0 send"
$ns at 1.7 "$p1 send"
$ns at 2.4 "$cbr stop"
$ns at 2.5 "$p0 send"
$ns at 3.0 "$p1 send"
$ns at 3.5 "Finish"

$ns run
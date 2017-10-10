# A Tool for Denial of Service Attack Testing in IoT

## Abstract

Internet of Things (IoT) has achieved rapid developments in recent years. Nevertheless, security has become a challenge for the stability and sustainability of IoT systems considering threatens from security attacks such as Man In the Middle Attack and Denial of Service (DoS), which have led to great loses. For this reason, risk analysis come to be of importance in IoT. In this project, we developed a security risk testing tool for IoT System, aiming to identify specific vulnerabilities so that related solutions could be studied for reinforcement. This tool is mainly focus on the threatens from DoS, and providing the function to test Sleep Deprivation Attack, Radio Frequency (RF) Interference, and Flooding Attack in DoS attacks. In this paper, an IoT system model will be constructed to perform the testing with this tool. We aim to call researcher’s attention to the three kinds of DoS attacks with the testing results described in this paper.

## Structure

![structure of DoS testing](https://github.com/CongBao/DoS-Tool-for-IoT-Testing/blob/master/model.png)

## How to use

+ mqttAtk

Installation

    $ sudo chmod +x ./mqttAtk.sh ./clear.sh
    $ sudo ln -s mqttAtk /usr/bin/

Using

    $ mqttAtk -u url -p port -n num_of_threads

+ rfiAtk

Installation

    $ sudo chmod +x ./rfi_init.sh ./rfi.sh
    $ sudo ln -s rfi_init /usr/bin
    $ sudo ln -s rfi /usr/bin

Using

    $ rfi_init -p port
    $ rfi -p port -d delay 

+ SleepDpAtk

Installation

    $ sudo chmod +x ./Sleepdp_attack.sh
    $ sudo ln -s Sleepdp_attack /usr/bin

Using

    $ Sleepdp_atk -p port -d delay
#
# Author: Saif Asif
# Version : 1.2
#
# Utility to calculate the time required to execite a certain command
#

date1=$(date +"%s")
# Your command here
date2=$(date +"%s")
diff=$(($date2-$date1))
echo "$(($diff / 60)) minutes and $(($diff % 60)) seconds elapsed."

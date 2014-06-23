#!/bin/python

#
# Will re-write the file with a new set of dates starting from the unix_epcoh variable
#
#

_author_ = "Saif Asif"

import re
import sys

unix_epoch = '2000/01/01 10:10:10'

def main(file_path):
	try:
		input_file = open(file_path, 'r')
		new_file = open(file_path+'_mod.csv', 'w')
		index =0
		for line in input_file:
			if "Domain" in line:
				print "Skipped header"
				continue
			new_file.write(line.replace(line, get_newly_dated_line(line)))
			print "Replaced line %s with %s" %(str(index), unix_epoch)
			index = index + 1
	except Exception, e:
		print e
		raise e
	finally:
		input_file.close()
		new_file.close()

def get_newly_dated_line(line):
	global unix_epoch
	new_epoch = get_new_date(unix_epoch)
	return re.sub(r"\d{4}\/\d{2}\/\d{2}\s\d{2}:\d{2}:\d{2}", new_epoch, line)

def get_new_date(current_epoch):

	date,time = current_epoch.split()
	date_elems = date.split('/')
	time_elems = time.split(':')
	
	year = int(date_elems[0])
	month = int(date_elems[1])
	day = int(date_elems[2])

	hour = int(time_elems[0])
	mins = int(time_elems[1])
	secs = int(time_elems[2])

	if day == 28:
		day = 1
		month = month + 1

	if month == 12:
		month = 1
		year = year + 1

	secs = secs + 1

	if secs == 59:
		secs = 0
		mins = mins + 1

	if mins == 59:
		mins = 0
		hour = hour + 1

	if hour == 23:
		hour = 0
		day = day + 1

	month_impl = str(month)
	if len(month_impl) == 1:
		month_impl = '0'+month_impl

	day_impl = str(day)
	if len(day_impl) == 1:
		day_impl = '0'+day_impl

	hour_impl = str(hour)
	if len(hour_impl) == 1:
		hour_impl = '0'+hour_impl

	secs_impl = str(secs)
	if len(secs_impl) == 1:
		secs_impl = '0'+secs_impl

	min_impl = str(mins)
	if len(min_impl) == 1:
		min_impl = '0'+min_impl

	global unix_epoch 
	unix_epoch = str(year) + '/' + month_impl + '/' + day_impl + ' ' + hour_impl + ':' + min_impl + ':' + secs_impl
	return unix_epoch

if __name__ == '__main__':
	if len(sys.argv) == 2:
        	main(sys.argv[1])
    	else:
        	print "Invalid arguments encountered.\nExpected filename"
	

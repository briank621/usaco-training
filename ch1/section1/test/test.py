import sys

infile = open(sys.argv[1],'r')
outfile = open('test.out' ,'w')

line = infile.readline().split(' ')
line = map(int, line)

out = sum(line)

print 3
outfile.write(str(out))

infile.close()
outfile.close()



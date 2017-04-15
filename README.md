# feed-reader

Filter and formats atom feed archives

## Usage

```bash
usage: feedreader [OPTIONS] URL
 -b,--backward             read the last feed entry from URL and move to
                           the beginning
 -D <property=value>       sets a system property value
 -f,--format <formatter>   print using specified formatter, available are:
                           XML, ENTRY
 -h,--help                 usage information
 -k,--unsecure             try insecure SSL connection
 -m,--match                print only the first matching entry
 -o,--output <file>        write to specified file instead of stdout
 -x,--xpath <expression>   print only entries with content that match
                           specified xpath 2.0 expression
```                           

## Examples

Assume feed content is XML and pretty print.

```bash
feedreader --format XML http://192.168.1.3:8090/sim/api/feed/1
```


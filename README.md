# feed-reader

Filter and formats atom feed archives

## Usage

```
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

Print feed content from specified URL starting from first entry. Follows any `next-archive`links.

```bash
feedreader http://192.168.1.3:8090/sim/api/feed/1
```
Print feed content from specified URL starting from last entry. Follows any `prev-archive`links.

```bash
feedreader --backward http://192.168.1.3:8090/sim/api/feed/recent
```

Assume feed content is XML and pretty print.

```bash
feedreader --format XML http://192.168.1.3:8090/sim/api/feed/1
```

Search feed entry content.

```bash
feedreader --xpath "//*:link/*:rel='order'" http://192.168.1.3:8090/sim/api/feed/1
```

Search for the first feed entry that matches the expression.

```bash
feedreader --match --xpath "//*:id='4555e3c7-8642-4b10-95b5-3433eb817411'" \
           http://192.168.1.3:8090/sim/api/feed/1
```

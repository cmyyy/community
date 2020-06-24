## Isolation community
    An isolated community where owner communicates with himself.
## Resources
[Spring](https://spring.io/guides)
[Spring Web](https://spring.io/guides/gs/serving-web-content)
[es](https://elasticsearch.cn/explore)
[Bootstrap](https://v3.bootcss.com/getting-started/)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/)
[spring boot](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
## Tools
Git
[Visual Paradigm](https://www.visual-paradigm.com)


## script
```sql
CREATE CACHED TABLE "PUBLIC"."USER"(
    "ID" INT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_CCB2A368_D475_4391_8568_061EE41918E1" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_CCB2A368_D475_4391_8568_061EE41918E1",
    "ACCOUNT_ID" VARCHAR(100),
    "NAME" VARCHAR(50),
    "TOKEN" VARCHAR(36),
    "GMT_CREATE" BIGINT,
    "GMT_MODIFIED" BIGINT
)
```

## Day01
Bug1: Can't connect to api.github.com when in `githubProvider.getUser();`.
[Soluiton](https://niter.cn/p/125)

## Day02



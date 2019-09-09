This is shared library where we put some shared things for all backend modules.

JwtTokenAuthenticationFilter is responsible to read Jwt token from request and to write it to both out custom Context and SecurityContextHolder context.

Context holds userId of currently logged in user.

Kafka beans are included only if includeKafka property is set to true.
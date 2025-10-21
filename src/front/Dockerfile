FROM oven/bun:canary-alpine

WORKDIR /app

COPY package*.json ./

RUN bun install

EXPOSE 3000

CMD ["bun", "dev"]

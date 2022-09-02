FROM ubuntu:latest
RUN apt-get update && apt-get install -y x11vnc xvfb openjdk-11-jdk
ADD robocode robocode
RUN echo "cd robocode && ./robocode.sh" > ~/.xinitrc && chmod +x ~/.xinitrc
#RUN rm -rf /robocode/robots/*
ADD competition_bots /robocode/robots/competition_bots
RUN cd /robocode/robots/ && javac -cp ../libs/robocode.jar competition_bots/*.java


CMD ["x11vnc","-ncache", "10", "-create", "-forever"]

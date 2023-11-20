# spring-boot-measure-execute-time
## Introduction
I recently worked closely with the capacity team, and they have requested the application team to supply data fields related to the "execution time for each transaction." This request aligns with the capacity team's overarching objective of sourcing pertinent data to support analysis, budgeting, resource allocation, and the forecasting of future application growth. The inclusion of transaction execution time is a specific requirement in this context.

Consequently, I have chosen to document my research findings in this blog to address the requirements of the capacity team.

## Execute Time
`Transaction execution time` refers to the duration starting from when a request is sent to our service, through the processing of that request, until the response is sent back to the client. It encapsulates the entire lifecycle of a transaction, measuring the time taken for the service to handle and complete the request and provide a response to the client.

Spring Boot offers a variety of methods for measuring execution time and other metric aspects. In this blog, I will illustrate a few approaches to measuring the execution time of transactions with practical examples.

## 1.Measuring execution time using Spring AOP
This is a very easy way that you just implement the AOP concept to capture logs, which I have a blog to provide an example of implementing here [spring-boot-centralized-logging-aop](https://github.com/santipabWannakiri/spring-boot-centralized-logging-aop)\
Once you've followed the provided example, you may need to make slight adjustments to the code, particularly in the @Around like the picture below.

<img src="images/capture-execute-time.png"  alt="image description" width="600" height="180">

## 2.Using Spring Boot Actuator

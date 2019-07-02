![Grade: 100](https://img.shields.io/badge/Grade-100-brightgreen.svg) 
[![Build Status](https://travis-ci.org/avisoftware/ray-tracing.svg?branch=master)](https://travis-ci.org/avisoftware/ray-tracing)
[![codecov](https://codecov.io/gh/avisoftware/ray-tracing/branch/master/graph/badge.svg)](https://codecov.io/gh/avisoftware/ray-tracing)
# Ray Tracing
This is a project that i did with @udcode in **Introduction to Software Engineering** course.  
This course is part of my bachelor's degree in Computer science at **JCT (Lev Academic Center)**.
## Description  
The project is a library that can create an image from scene, in a technique called "Ray Tracing"
### The Goals of the project
The goal of this project wasn't to create beautiful images and great scenes,   
But to learn **Software Engineering** principal's.  
Like:
* **TDD**
* **Agile** Development
* Design Patterns

### Key Features
- [x] Basic Ray Tracing
- [x] Lights
- [x] Shadow
- [x] Reflections
- [x] Transparency 
- [x] Depth Of Field
- [x] MultiThread 
- [x] Grid (Performance)
 
###### Not Implemented Yet
- [ ] More Geometries
- [ ] Soft Shadows
- [ ] Super Sampling
- [ ] Glossy Material 
- [ ] Pattern Material 

### Performance
When we talk about **Ray Tracing**, performance it's always the biggest issue.  
The more you want your image to be more realistic, you'll have a performance issue.  
I chose to implement the Grid method to improve Performance.

### Examples
![street](https://github.com/avisoftware/ray-tracing/blob/master/images/advancedStreet.jpg)  
*The image holds 1529 Geometries and 34 Lights*
*Runtime with 4 cores and Grid - 111.382 s. without Grid 480.888 s*
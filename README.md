Opening Hours
=============

[JL coding challenge 25](https://coding-challenges.jl-engineering.net/challenges/challenge-25/)

------

## Instructions

An app that aggregates information about retail businesses shows the opening hours for each shop.

The app shows the opening hours on a single line of text which is obtained from a 3rd party Api.

Unfortunately the Api has changed so that it no longer returns a single line of text,
and instead returns data as an array of opening times,
each containing array of days that the opening times apply to.

If opening time and closing time is both 00:00 then the business is closed on that day.

In this challenge you are required to create a function to convert the array of opening times into a single line of text.

It's up to you what the resulting text looks like as long as it fits the conflicting requirement of being both understandable and compact.
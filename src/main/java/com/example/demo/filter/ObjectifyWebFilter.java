package com.example.demo.filter;

import com.googlecode.objectify.ObjectifyFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/*"})
public class ObjectifyWebFilter extends ObjectifyFilter {}

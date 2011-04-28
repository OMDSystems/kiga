#!/usr/bin/env ruby

public_folder = File.join(File.expand_path(File.dirname(__FILE__)), '..', 'public')

system "coffee -o #{public_folder}/javascripts --compile --bare #{public_folder}/coffeescripts/*.coffee"
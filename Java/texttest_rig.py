#!/usr/bin/env python
"""
This script uses Maven to execute the TexttestFixture.
It is designed to be used by TextTest and specified in the file 'texttests/config.gr' in this repo.
It is more convenient for TextTest to use since Maven needs
several arguments in addition to the one the TextTest fixture needs.
"""
import os
import subprocess
import sys

args = " ".join(sys.argv[1:])
TEXTTEST_HOME = os.environ.get("TEXTTEST_HOME", os.getcwd())
java_dir = os.path.join(TEXTTEST_HOME, "")

subprocess.run(
    f'./mvnw -q test-compile exec:java -Dexec.mainClass="com.gildedrose.TexttestFixture" -Dexec.args="{args}"',
    shell=True,
    cwd=java_dir
)

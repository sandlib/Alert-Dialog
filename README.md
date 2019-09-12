`Gradle`
_______

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


dependency
__________

dependencies {
	        implementation 'com.github.sandlib:Alert-Dialog:Tag'
	}


Maven
______

<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

dependency:
_________

<dependency>
	    <groupId>com.github.sandlib</groupId>
	    <artifactId>Alert-Dialog</artifactId>
	    <version>Tag</version>
	</dependency>